<?php
	$theFiles = array();
	$revFiles = array();
	//Set cache to false on ajax calls
	/**
	 * The class projects, holds relevant data plus relevant files
	 */
	class Project
	{
		public $_title;
		public $_date;
		public $_version;
		public $_author;
		public $_summary = "No Commit Message Given";
    	function __construct() 
    	{
       		$this->_title = '';
       		$this->_date = '';
       		$this->_version = 0;
       		$this->_summary = 'No Commit Message Given';
    	}
	}

	class commentNode
	{
		public $next;
   	 	public $previous;
		public $id;
		public $comment;
		public $isResponse;

		function __construct() {
			$this->next = NULL;
			$this->previous = NULL;
			$this->id = 0;
			$this->comment = "";
			$this->isResponse = False;
		}
	}

	/**
	 * Works as the 'controller' for the whole process
	 * @param  string $list    the filepath to svn list
	 * @param  string $log     filepath to svn log
	 * @param  array $projArr projects array
	 * @return nothing          null
	 */
	function initProject($list, $log, $projArr)
	{
		$con = mysql_connect("engr-cpanel-mysql.engr.illinois.edu", "bailey27_bailey", "password1");
		if (!$con)	
		{
    		die('Could not connect: ' . mysql_error());
		}
		mysql_select_db("bailey27_comments", $con);
		$logObj = simplexml_load_file($log);
		$listObj = simplexml_load_file($list);
		foreach($listObj->children() as $list){
			$lastName = 'None';
			foreach($list->children() as $entry){
				$type = 'Code';	$path = 'Path';	$author = 'Me';	$date = 'Today';
				$flag = 0; $size = 0; $version = 0;
				$newProj = new Project();
				foreach($entry->children() as $child){
					if($child->getName() == "name")	{
						$child = (string)$child;
						if(strstr($child, $lastName) != ''){
							$flag = 1;
							$path = (string)$child;
							if(stristr($path, 'test')!= '')	
								$type = 'Test';
							if(((string)$entry->attributes()) == 'dir')	
								continue;
						}
						else{
							$flag = 0;
							$newProj->title = $child;
							$lastName = (string)$child;
						}
					}
					else if($child->getName() == "size")
						$size = (int)$child;
					else if($child->getName() == "commit"){
						if($flag == 0)
							projFn($newProj, $child);
						else{
							$version = (int)$child->attributes();
							foreach($child->children() as $entries){
								if($entries->getName() == "author")
									$author = (string)$entries;
								else if($entries->getName() == "date")
									$date = (string)$entries;
							}		
						}			
					}
					if($flag == 1 && $size != 0 && $version != 0 && $path != 'Path' && $date != 'Today' && $author != 'Me'){
						$newFile = new File($size, $type, $path, $version, $author, $date);
						$parts = explode("/", $path);
						if(is_null($theFiles[$parts[0]])){
							$theFiles[$parts[0]] = array();
							array_push($theFiles[$parts[0]], $newFile);
						}
						else
							array_push($theFiles[$parts[0]], $newFile);
					}
				}
				if($flag == 0)
					$projArr[$newProj->title] = $newProj;
			}
		}
		getRev($projArr, $theFiles, $logObj, 1);
	}

	/**
	 * Sets the relevant data for the project
	 * @param  the new project $newProj the new project
	 * @param  the iterator $child   the iterator
	 * @return nothing          nothing
	 */
	function projFn($newProj, $child)
	{
		$newProj->version = (int)$child->attributes();
		foreach($child->children() as $entries)
		{
			if($entries->getName() == "author")
			{
				$newProj->author = (string)$entries;
			}
			else if($entries->getName() == "date")
			{
				$newProj->date = (string)$entries;
			}
		}
	}

	/**
	 * Fetches the data from the svn_log for revisions
	 * @param  array $projArr  Holds the projects thus far
	 * @param  array $theFiles Holds the files thus far
	 * @param  simplexml $logObj   olds the log data
	 * @return nothing           null
	 */
	function getRev($projArr, $theFiles, $logObj, $flag)
	{
		foreach($logObj->children() as $log)
		{
			$author = 'Me';
			$date = 'Today';
			$path = 'Code';
			$path = 'Here';
			$version = (int)$log->attributes();
			foreach($log->children() as $entry)
			{
				if($entry->getName() == 'author')
				{
					$author = (string)$entry;
				}
				else if($entry->getName() == 'date')
				{
					$date = (string)$entry;
				}
				else if($entry->getName() == 'paths')
				{
					foreach($entry->children() as $paths)
					{
						$path = (string)$paths;
						$pos = strpos($path, 'bailey27/');
						$path = substr($path, $pos+9);
						$newFile = new File(0, 'Code', $path, $version, $date, $author);
						$parts = explode("/", $path);
						if(is_null($revFiles[$parts[1]])){
							$revFiles[$parts[1]] = array();
							array_push($revFiles[$parts[1]], $newFile);
						}
						else
							array_push($revFiles[$parts[1]], $newFile);
						}
				}
			}
		}
		if($flag == 1)
		{
			printTabsTable($projArr, $theFiles, $revFiles);
		}
	}

	/**
	 * Simply prints out the specific information on the projects
	 * @param  array $projArr  holds the projects
	 * @param  array $theFiles holds the files
	 * @return null           nothing
	 */
	function printTabsTable($projArr, $theFiles, $revFiles)
	{
		echo "<div id=\"wrapper\">";
		echo "<div id=\"tabContainer\"><div id=\"tabs\"><ul>";
      	$counter = 1;
      	foreach($projArr as $proj){
        	echo "<li id=\"tabHeader_" . $counter . "\">" . $proj->title . "<a href='#'>" . $proj->title . "</a></li>";
        	$counter++;
      	}
      	$proj = '';
      	echo "</ul></div>";
		echo "<div id=\"tabscontent\">";
   	    $counter = 1;
   		foreach($theFiles as $file)	{
   			echo "<div class = \"tabpage\" id=\"tabpage_" . $counter . "\">";
   	    	$secCounter = 0;
   			foreach($file as $files){
   				//File table starts here
   				if($secCounter == 0)
   				{
					$parts = explode("/", $files->_path);
					$proj = $projArr[$parts[0]];
					echo "<h4>Specific Project Information</h4>";
					echo "<table class=\"table table-striped table-bordered table-hover\">";
					echo "<tr><th>Project Title</th><th>Project Date</th><th>Most Recent Revision</th><th>Project Summary</th><th>Author</th></tr>";
					echo "<tr><td>" . $proj->title . "</td><td>" . $proj->date . "</td><td>" . $proj->version . "</td><td>No Message Given</td><td>" . $proj->author . "</td></tr></table>";
   			   		echo "<h4>File Information</h4>";
   				}
   				echo "<table class=\"table table-striped table-bordered table-hover\">";
   				echo "<tr><th>File Name</td><th>File Size</th><th>File Type</th><th>File Date</th><th>File Link</th></tr>";
   				echo "<tr><td>" . $files->_path . "</td>";
   				echo "<td>" . $files->_size . "</td>";
   				echo "<td>" . $files->_type . "</td>";
   				echo "<td>" . $files->_author . "</td>";
   				$temp = 'https://subversion.ews.illinois.edu/svn/fa12-cs242/bailey27/' . $files->_path;
   				echo "<td><a href=$temp>View the File</a></td></tr>";
   				echo "<tr><td>Revisions:</td>";
   				echo "<td colspan=\"4\">";
  				//Revision Table
   				echo "<table class=\"table table-striped table-bordered table-hover\">";
   				echo "<tr><th>Date</th><th>Author</th><th>Version</th><th>Path</th><th>Message</th></tr>";
   				$count = 0;
   				foreach($revFiles as $rev){
   					foreach($rev as $revs){
   						if(strstr($revs->_path, $files->_path) != ''){
   							echo "<tr>";
   							echo "<td>" . $revs->_date . "</td>";
   							echo "<td>" . $revs->_author . "</td>";
   							echo "<td>" . $revs->_version . "</td>";
   							echo "<td>" . $revs->_path . "</td>";
   							echo "<td>No message given</td>";
   							echo "</tr>";
   							$count++;
   						}
   					}
   				}
   				if($count == 0)
   				{
   					echo "<tr><td>No Revisions Were Made To This File For This Assignment</td></tr>";
   				}
   				//End revision table, it's row, and total table
      			echo "</table></table></br>";
      			if(end($file) == $files)
      			{
      				commentsFn($projArr, $proj);
      			}
   				$secCounter += 1;
   			}
   			echo "</div>";
   			$counter++;

   		}
    	echo "</div>";
  		echo "</div>";
  		echo "</div>";
		echo "<script src=\"tabber.js\"></script>";		
	}

	/**
	 * Works with the comments
	 * @param  array $projArr holds the projects
	 * @param  string $proj    the name of the project
	 * @return none          none
	 */
	function commentsFn($projArr, $proj)
	{
		echo "<h4>Comments On The Projects</h4>";
		$title = (string)$proj->title;
		$userCom = mysql_query("SELECT Comment FROM Comments WHERE Project = '$proj->title'");
		$counter = 0;
		$commentStuff = array();
		$head = NULL;
		$iterator = NULL;
		while($row = mysql_fetch_assoc($userCom))
		{
			$commentStuff[$counter] = $row;
			$counter++;
		}		
		$counter = 0;
		for($i = 0; $i < count($commentStuff); $i++)
		{
			foreach($commentStuff[$i] as $kk)
			{
				$temp = new commentNode();
				$temp->next = NULL;
				$temp->prev = NULL;
				if($counter == 0)
				{
					$head = $temp;
					$iterator = $temp;
					$head->prev = NULL;
					$head->next = NULL;
				}
				else
				{
					$iterator->next = $temp;
					$temp->prev = $iterator;
					$iterator = $temp;
					$temp->next = NULL;
				}
				$temp->comment = $kk;
				$counter++;
			}
		}
		$userCom = mysql_query("SELECT Comment_ID FROM Comments WHERE Project = '$proj->title'");
		$counter = 0;
		$commentStuff = array();
		while($row = mysql_fetch_assoc($userCom))
		{
			$commentStuff[$counter] = $row;
			$counter++;
		}		
		$iterator = $head;
		for($i = 0; $i < count($commentStuff); $i++)
		{
			foreach($commentStuff[$i] as $kk)
			{
				$iterator->id = $kk;
				$iterator = $iterator->next;
			}
		}		
		$userCom = mysql_query("SELECT Parent FROM Comments WHERE Project = '$proj->title'");
		$counter = 0;
		$commentStuff = array();
		while($row = mysql_fetch_assoc($userCom))
		{
			$commentStuff[$counter] = $row;
			$counter++;
		}		
		$iterator = $head;
		for($i = 0; $i < count($commentStuff); $i++)
		{
			foreach($commentStuff[$i] as $kk)
			{
				if($kk == 0)
				{
					$iterator->isResponse = False;
				}
				else
				{
					$secIterator = $head;
					while($secIterator->id != $kk)
					{
						$secIterator = $secIterator->next;
					}
					if($iterator->next != NULL)
					{
						$iterator->next->prev = $iteratpr->prev;
					}
					if($iterator->prev != NULL)
					{
						$iterator->prev->next = $iterator->next;
					}
					$iterator->prev = $secIterator;
					$iterator->next = $secIterator->next;
					if($secIterator->next != NULL)
					{
						$secIterator->next->prev = $iterator;
					}
					$secIterator->next = $iterator;
					$iterator->isResponse = True;
				}
				$iterator = $iterator->next;
			}
		}
		handleComments($head, $proj);		
	}

	/**
	 * Actually prints the comments themselves
	 * @param  pointer $head points to the head of the comments list
	 * @return nothing       zilch
	 */
	function handleComments($head, $proj)
	{
		$iterator = $head;
		echo "<ul>";
		while($iterator != NULL)
		{
			if($iterator->isResponse == 0)
			{
				echo "<li>Comment " . $iterator->id . "- " . $iterator->comment . "</li>";
			}
			else
			{
				$counter = 0;
				$secIterator = $iterator;
				while($secIterator->isResponse == 1)
				{
					$counter++;
					echo "<ul>";
					$secIterator= $secIterator->prev;
				}
				echo "<li>Comment " . $iterator->id . "- " . $iterator->comment . "</li>";
				for($i = 0; $i < $counter; $i++)
				{
					echo "</ul>";
				}
			}
			$iterator = $iterator->next;
		}
		echo "</ul>";
		getComments($proj);
	}

	function getComments($proj)
	{
		echo "<form method=\"post\" action=\"inputhandler.php\">";
		echo "Enter your comment here: </br><textarea name=\"textarea\" rows=\"3\"></textarea></br>";
		echo "If it's a reply to a comment, enter the comment number here: </br><textarea name=\"reply\" rows=\"1\"></textarea></br>";
  		echo "<input type = \"submit\">";
  		echo "<textarea name=\"assignment\" style=\"display:none;\">$proj->title</textarea>";
  		echo "</form>";
	}
?>