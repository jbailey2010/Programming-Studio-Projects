	<?php
		/**
		 * The class projects, holds relevant data plus relevant files
		 */
		class Project
		{
			public $_title;
			public $_date;
			public $_version;
			public $_summary = "No Commit Message Given";
			public $_author;
	    	function __construct() 
	    	{
	       		$this->_title = '';
	       		$this->_date = '';
	       		$this->_version = 0;
	       		$this->_summary = 'No Commit Message Given';
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
			echo "<table class=\"table table-striped table-bordered table-hover\"><h2>General Project Information</h2>";
			echo "<tr><td>Project Title</td><td>Project Date</td><td>Most Recent Revision</td><td>Project Summary</td><td>Author</td>";
			foreach($projArr as $proj)
				echo"<tr><td>" . $proj->title . "</td><td>" . $proj->date . "</td><td>" . $proj->version . "</td><td>" . "No Commit Message Given" . "</td><td>" . $proj->author . "</td></tr>";
			echo "</table>";
			printTabs($projArr, $theFiles);
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
		 * Simply prints out the specific information on the projects
		 * @param  array $projArr  holds the projects
		 * @param  array $theFiles holds the files
		 * @return null           nothing
		 */
		function printTabs($projArr, $theFiles)
		{
			echo "<h2>Specific Project Information</h2>";
			echo "<div id=\"wrapper\">";
			  	echo "<div id=\"tabContainer\">";
	    			echo "<div id=\"tabs\">";
	      				echo "<ul>";
	      					$counter = 1;
	      					foreach($projArr as $proj)
	      					{
	        					echo "<li id=\"tabHeader_" . $counter . "\">" . $proj->title . "<a href='#'>" . $proj->title . "</a></li>";
	        					$counter++;
	      					}
	      				echo "</ul>";
	    			echo "</div>";
	    			echo "<div id=\"tabscontent\">";
	    			    $counter = 1;
	    				foreach($theFiles as $file)
	    				{
	    					echo "<div class = \"tabpage\" id=\"tabpage_" . $counter . "\">";
	   							echo "<ul>";
	    						foreach($file as $files)
	    						{
	    							echo "<li>File Name" . $files->_path . "</li>";
	    							echo "<ul>";
	    							echo "<li>Size: " . $files->_size . "</li>";
	    							echo "<li>Type: " . $files->_type . "</li>";
	    							echo "<li>Date: " . $files->_date . "</li>";
	    							$temp = 'https://subversion.ews.illinois.edu/svn/fa12-cs242/bailey27/' . $files->_path;
	    							echo "<li><a href=$temp>View the File</a></li>";
	    							echo "<li>MAKE THIS A LINK TO OLD REVISIONS</li>";
	    							echo "</ul>";
	    							echo "</br>";
	    						}
	    						echo "</ul>";
	    					echo "</div>";
	    					$counter++;
	    				}
	    			echo "</div>";
	  			echo "</div>";
	  		echo "</div>";
			echo "<script src=\"tabber.js\"></script>";		

			revButton();
		}

		function revButton()
		{
			echo "<FORM METHOD=\"LINK\" ACTION=\"ActualCode.php\">";
				echo "<INPUT TYPE=\"submit\" VALUE=\"Press to see without revisions\">";
			echo "</FORM>";
		}
?>