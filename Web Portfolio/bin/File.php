<?php
	/**
	 * The file class, holds the relevant data, nothing else.
	 */
	class File
	{
		public $_size;
		public $_type = 'Code';
		public $_path;
		public $_version;
		public $_author;
		public $_date;
		#iframe isn't an object
		public $_alternateFiles = null;

    	function __construct($size, $type, $path, $version, $date, $author) 
    	{
       		$this->_size = $size;
       		$this->_type = $type;
       		$this->_path = $path;
    		$this->_alternateFiles = null;
    		$this->_version = $version;
    		$this->_date = $date;
    		$this->_author = $author;
    	}
	}





?>