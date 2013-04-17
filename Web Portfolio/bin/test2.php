<?php
	//For the sake of some sort of a test suite
	include("Test.php");

	/**
	 * Tests the three new classes to make sure they all work as they should
	 * @return nothing no return
	 */
	function testNewClasses()
	{
		$testProj = new Project();
		if($testProj->_title == '' && $testProj->_date == '' && $testProj->_version == 0)
		{
			echo "8) PASS! The new class constructor worked as it should</br>";
		}
		else
		{
			echo "8) FAIL! The new class constructor failed!</br>";
		}
		$testFile = new File(0, 'Code', 'Path', 1, 'bailey27', 'today');
		if($testFile->_size == 0 && $testFile->_type == 'Code' && $testFile->_path == 'Path' && $testFile->_version == 1)
		{
			echo "9) PASS! The second new class constructor worked correctly</br>";
		}
		else
		{
			echo "9) FAIL! The second new class constructor failed in the inputs</br>";
		}
		$testCommentNode = new commentNode();
		if($testCommentNode->_id == 0 && $testCommentNode->_next == NULL && $testCommentNode->_prev == NULL)
		{
			echo "10) PASS! The third new class constructor worked as it should</br>";
		}
		else
		{
			echo "10) FAIL! The third new constructor isn't setting the baseline values correctly</br>";
		}
	}


	/**
	 * Strips the tags off of the string and then removes any 
	 * sql injection
	 * @param  string $input the input string
	 * @return string        the safe string
	 */
	function secureInput($input)
	{
		$input = strip_tags($input);
		$input = mysql_escape_string($input);
		return $input;
	}

	/**
	 * Makes sure secure input works as it should
	 * @return nothing no return
	 */
	function testSecureInputTags()
	{
		$tagString = "<a href='http://facebook.com'>Hello</a>";
		if($tagString != 'Hello')
		{
			echo "19) PASS! The string is currently bad</br>";
		}
		else
		{
			echo "19) FAIL! It does not read the tags</br>";
		}
		$tagString = secureInput($tagString);
		if($tagString == 'Hello')
		{
			echo "11) PASS! The tag removal worked as it should!</br>";
		}
		else
		{
			echo "11) FAIL! What should've been Hello was " . $tagString . "</br>";
		}
		$tagString = "<a><h2>Heading</h2></a>";
		if($tagString != 'Heading')
		{
			echo "20) PASS! It's an invalid string</br>";
		}
		else
		{
			echo "20) FAIL! It's reading it without the tags...</br>";
		}
		$tagString = secureInput($tagString);
		if($tagString == 'Heading')
		{
			echo "12) PASS! The multi-level tags were stripped</br>";
		}
		else
		{
			echo "12) FAIL! The multi-level tags weren't appropriately stripped</br>";
		}
		$tagString = "<body><a><h2><p>Stuff</p></h2></a></body>";
		if($tagString != 'Stuff')
		{
			echo "21) PASS! It's an invalid string!</br>";
		}
		else
		{
			echo "22) FAIL! It's reading the content, not the tags</br>";
		}
		$tagString = secureInput($tagString);
		if($tagString == 'Stuff')
		{
			echo "13) PASS! The 4-level tags were stripped</br>";
		}
		else
		{
			echo "13) FAIL! The 4-level tags were NOT stripped!</br>";
		}
	}

	/**
	 * Tests that sql injection fails
	 * @return nothing null
	 */
	function testSecureInputSQL()
	{
		$testString="SELECT * FROM Comments WHERE 't'='t'";
		$testString2 = secureInput($testString);
		if($testString2 != $testString)
		{
			echo "14) PASS! SQL Injection Foiled</br>";
		}
		else
		{
			echo "14) FAIL! It was the same but shouldn't have been</br>";
		}
		$harderString = "SELECT * FROM Comments WHERE Project = 'Nothing' OR 't'='t'";
		$testHarder = secureInput($harderString);
		if($testHarder != $harderString)
		{
			echo "15) PASS! Harder SQL Injection foiled</br>";
		}
		else
		{
			echo "15) FAIL! It should've changed but did not</br>";
		}
		$superHard = "SELECT * FROM Comments WHERE Project = 'Nothing' OR 't'='t' OR 'x'='x' OR 't'='x'";
		$testHardest = secureInput($superHard);
		if($testHardest != $superHard)
		{
			echo "16) PASS! Hardest and last SQL Injection failed</br>";
		}
		else
		{
			echo "16) FAIL! SQL Injection succeeded but shouldn't have</br>";
		}
	}

	/**
	 * Finds the max id used, adds one to it, and returns it
	 * @return int new max int value for id
	 */
	function maxID()
	{
		$query = "SELECT MAX(Comment_ID) FROM Comments";
		$return = mysql_fetch_row(mysql_query($query));
		$returnInt = $return[0];
		$returnInt += 1;
		return $returnInt;
	}

	/**
	 * A basic test to make sure maxID works as it should
	 * @return nothing no return
	 */
	function testMaxID()
	{
		$max = 0;
		$max = maxID();
		if($max > 0)
		{
			echo "17) PASS! It fetched a new max!</br>";
			$max2 = maxID();
			if($max2 == $max)
			{
				echo "18) PASS! It consistently fetches a maximum!</br>";
			}
			else
			{
				echo "18) FAIL! It gets a new max when it shouldn't</br>";
			}
		}
		else
		{
			echo "17) FAIL! It didn't fetch a positive number";
		}
	}

	/**
	 * Fixes any dirty words in the input
	 * @param  string $input the comment itself
	 * @return string        the comment cleaned up
	 */
	function fixDirtyWords($input)
	{
		$badWords = mysql_query("SELECT * FROM naughty");
		$badArr = array();
		$replArr = array();
		$counter = 0;
		while($row = mysql_fetch_assoc($badWords))
		{
			$badArr[$counter] = $row["bad_words"];
			$replArr[$counter] = $row["less_naughty"];
			$counter++;
		}
		for($i = 0; $i < $counter; $i++)
		{
			$input = str_replace($badArr[$i], $replArr[$i], $input);
		}
		return $input;
	}

	/**
	 * Tests the removal of 'dirty words'
	 * @return nothing no return
	 */
	function testDirtyWordRemoval()
	{
		$oneWord = 'Damn';
		$newWord = fixDirtyWords($oneWord);
		if($oneWord != $newWord)
		{
			echo "22) PASS! A basic word removal was implemented</br>";
		}
		else
		{
			echo "22) FAIL! The word wasn't removed, but it should have been</br>";
		}
		$twoWord = 'Damn Pig';
		$newTwoWord = fixDirtyWords($twoWord);
		if(strstr($newTwoWord, 'Damn') == '')
		{
			echo "23) PASS! One of the two words was removed</br>";
		}
		else
		{
			echo "23) FAIL! One of the two words wasn't removed</br>";
		}
		if(strstr($newTwoWord, 'Pig') == '')
		{
			echo "24) PASS! The other word was removed!</br>";
		}
		else
		{
			echo "24) FAIL! One of the two words wasn't removed</br>";
		}
	}

	/**
	 * Tests by inputting a comment and adding a reply to it
	 * verify with checking the size of the total relation
	 * @return [type] [description]
	 */
	function testInputOutput()
	{
		$commentString = 'TestComment1';
		$response = 0;
		$input = secureInput($commentString);
		$assignment = 'Assignment2.0';
		$id = maxID();
		$input = fixDirtyWords($input);
		$size = mysql_query('SELECT * FROM Comments');
		$size = mysql_num_rows($size);
		if($size == 0)
		{
			echo "25) FAIL! What should've been a valid size was not</br>";
		}
		else
		{
			echo "25) PASS! The size was fetched correctly</br>";
		}
		$inputQuery = "INSERT INTO `Comments`(`Comment_ID`, `Parent`, `Comment`, `Project`)
		 VALUES ($id,$response,'$input','$assignment')";	
		mysql_query($inputQuery);
		$newSize = mysql_query('SELECT * FROM Comments');
		$newSize = mysql_num_rows($newSize);
		if($newSize != $size && $newSize != 0)
		{
			echo "26) PASS! The comment was entered successfully</br>";
		}
		else
		{
			echo "26) FAIL! The comment wasn't entered successfully</br>";
		}

		$commentString = 'TestComment1 Reply';
		$response = $id;
		$input = secureInput($commentString);
		$assignment = 'Assignment2.0';
		$id = maxID();
		$input = fixDirtyWords($input);
		$size = mysql_query('SELECT * FROM Comments');
		$size = mysql_num_rows($size);
		if($size == 0)
		{
			echo "27) FAIL! What should've been a valid size was not</br>";
		}
		else
		{
			echo "27) PASS! The size was fetched correctly on the tricky iteration</br>";
		}
		$inputQuery = "INSERT INTO `Comments`(`Comment_ID`, `Parent`, `Comment`, `Project`)
		 VALUES ($id,$response,'$input','$assignment')";	
		mysql_query($inputQuery);
		$newSize = mysql_query('SELECT * FROM Comments');
		$newSize = mysql_num_rows($newSize);
		if($newSize != $size && $newSize != 0)
		{
			echo "28) PASS! The comment was entered successfully</br>";
		}
		else
		{
			echo "28) FAIL! The comment wasn't entered successfully</br>";
		}
	}

	//This is the de facto main section of the tests
	//First, connecting to the database for the more intricate tests
	$con = mysql_connect("engr-cpanel-mysql.engr.illinois.edu", "bailey27_bailey", "password1");
	if (!$con)	
	{
    	die('Could not connect: ' . mysql_error());
	}
	mysql_select_db("bailey27_comments", $con);
	//Then calling the various functions to actually test them
	testNewClasses();
	testSecureInputTags();
	testSecureInputSQL();
	testMaxId();
	testDirtyWordRemoval();
	testInputOUtput();
?>