'''
Created on Sep 30, 2012
Holds the 'node' data of the graph, the specific information about each airport
@author: Jeff
'''
import json
import urllib2
import pprint

class Airport:
    '''
    The airport is the 'node' of the graph, this holds the data of each
    classdocs
    '''


    def __init__(self, code, name, country, continent, timezone, coordinates, population, region):
        '''
        Sets the function data, holding all of the information of each airport to 
        the json data
        '''
        self.code = code
        self.name = name
        self.country = country
        self.continent = continent
        self.timezone = timezone
        self.coordinates = coordinates
        self.population = population
        self.region = region
    
    def printCityInfo(self):
        '''
        Simply prints out the information about the specified airport given
        user specification as to which one
        '''
        print 'The city code is', self.code
        print 'The city name is', self.name
        print 'The country in which this city is', self.country
        print 'The continent in which this city is', self.continent
        print 'The timezone of this city is', self.timezone
        nCoord = 0
        eCoord = 0
        counter = 0
        for coords in self.coordinates:
            if counter == 0:
                nCoord = self.coordinates[coords]
            else:
                eCoord = self.coordinates[coords]
            counter += 1
        print 'The coordinates of this city are', eCoord, "degrees East,", nCoord, "degrees North"
        print 'The population of this city is', self.population
        print 'The region of this city is', self.region
