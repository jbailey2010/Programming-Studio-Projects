<?php
	//use a header to go back to actual code
	
	
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

	$con = mysql_connect("engr-cpanel-mysql.engr.illinois.edu", "bailey27_bailey", "password1");
	if (!$con)	
	{
    	die('Could not connect: ' . mysql_error());
	}
	mysql_select_db("bailey27_comments", $con);
	$commentString = $_POST["textarea"];
	$response = $_POST["reply"];
	if($response == '')
	{
		$response = 0;
	}
	$response = (int)$response;
	$input = secureInput($commentString);
	$assignment = $_POST["assignment"];
	$id = maxID();

	$input = fixDirtyWords($input);

	$inputQuery = "INSERT INTO `Comments`(`Comment_ID`, `Parent`, `Comment`, `Project`)
	 VALUES ($id,$response,'$input','$assignment')";	
	mysql_query($inputQuery);


	header("Location: http://web.engr.illinois.edu/~bailey27/ActualCode.php");
?>