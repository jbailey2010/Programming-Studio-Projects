<?php
	include("File.php");
	include("Project.php");
	$listO;
	$logO;
	/**
	 * Initializes the two xml variables
	 * @param  xmlObj $listObj holds the list data
	 * @param  xmlObj $logObj  holds the log data
	 * @return nothing          null
	 */
	function initParts($list, $log)
	{
		$logO = simplexml_load_file($log);
		$listO = simplexml_load_file($list);
		echo "1) Initialization (reading in of xml) test ";
		if(is_null($logO) || is_null($listO))
		{
			echo "FAILED! One was null</br>";
		}
		else
		{
			echo "Passed!</br>";
		}
	}

	/**
	 * A unit test of sorts
	 * @param  The xml list $listObj still
	 * @param  the xml log $logObj  still
	 * @param  the project array $projArr 
	 * @return returns          nothing
	 */
	function getProjFile($listObj, $logObj, $projArr)
	{
		$logObj = simplexml_load_file('testxmllist.xml');
		$listObj = simplexml_load_file('testxmllog.xml');
		foreach($listObj->children() as $list){
			$lastName = 'None';
			foreach($list->children() as $entry){
				$type = 'Code';	$path = 'Path';	$author = 'Me';	$date = 'Today';
				$flag = 0; $size = 0; $version = 0;
				$newProj = new Project();
				foreach($entry->children() as $child){
					if($flag != 0 && $flag != 1)
					{
						echo "2) FAILED! The flag get's changed somewhere</br>";
						return;
					}
					if($child->getName() == "name")	{
						$child = (string)$child;
						if(strstr($child, $lastName) != ''){
							$flag = 1;
							$path = (string)$child;
							if($path == '')
							{
								echo '7) FAILED! The path should not be empty but is</br>';
								return;
							}
							if(stristr($path, 'test')!= '')	
								$type = 'Test';
							if($type != 'Test' && $type != 'Code')
							{
								echo "3) FAILED! Test gets mis-set when it shouldn't</br>";
								return;
							}
							if(((string)$entry->attributes()) == 'dir')	
								continue;
						}
						else{
							$flag = 0;
							$newProj->title = $child;
							if($newProj->title == '')
							{
								echo "4) FAILED! The title is empty when it shouldn't be</br>";
								return;
							}
							$lastName = (string)$child;
							if($lastName == '')
							{
								echo "5) FAILED! The lastname is empty when it shouldn't be.</br>";
								return;
							}
						}
					}
					else if($child->getName() == "size")
						$size = (int)$child;
					if($size < 0)
					{
						echo "6) FAILED! The size is an invalid number</br>";
						return;
					}
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
		echo "2-7) PASS! Lot's of tests in 1 function</br>";
	}

	//A series of unit tests
	initParts('testxmllist.xml', 'testxmllog.xml');
	$proj = array();
	getProjFile($listO, $logO, $proj);


?>