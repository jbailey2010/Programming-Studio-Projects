'''
Created on Sep 30, 2012

@author: Jeff
'''
import unittest
from Code.Airline import Airline
from Code.Route import Route
airlineObj = Airline()
file = 'https://wiki.engr.illinois.edu/download/attachments/207292263/map_data.json?version=1&modificationDate=1348635625000'
airlineObj.airlineInit(airlineObj.airportDic, file)
airlineObj.adjInit(airlineObj.adjList, airlineObj.adjOneWay, file)
airlineObj.setSources(airlineObj.sources, file)
airlineObj.getDists()
airlineObj.cityOps()
'''
A series of tests  on my functions in the airline class
'''
class Test(unittest.TestCase):
    
    '''
    A total test of the city variables
    48 airports, and 94 flights
    '''
    def testCityTot(self):
        minPop = airlineObj.minCityPop
        minPopName = airlineObj.minCityName
        self.assertEquals(minPop, 589900)
        self.assertEquals(minPopName, "Essen")
        maxPop = airlineObj.maxCityPop
        maxPopName = airlineObj.maxCityName
        self.assertEquals(maxPop, 34000000)
        self.assertEquals(maxPopName, "Tokyo")
        avgPop = airlineObj.avgCityPop
        self.assertAlmostEquals(avgPop, 11796143)
        airlineObj.showContList()
        self.assertEqual(len(airlineObj.contList), 6)
        self.assertTrue("North America" in airlineObj.contList)
        self.assertTrue("Asia" in airlineObj.contList)
        self.assertTrue("Europe" in airlineObj.contList)
        self.assertTrue("South America" in airlineObj.contList)
        self.assertTrue("Africa" in airlineObj.contList)
        self.assertTrue("Australia" in airlineObj.contList)
        airportSize = 48
        self.assertEquals(airportSize, len(airlineObj.airportDic))

    '''
    Checks to make sure that the continent list is the size it should be
    '''
    def testContListSize(self):
        airlineObj.showContList()
        self.assertEqual(len(airlineObj.contList), 6)
        
    '''
    Tests to make sure the keys of the continent list are what they should be
    '''
    def testContListConts(self):
        airlineObj.showContList()
        self.assertTrue("North America" in airlineObj.contList)
        self.assertTrue("Asia" in airlineObj.contList)
        self.assertTrue("Europe" in airlineObj.contList)
        self.assertTrue("South America" in airlineObj.contList)
        self.assertTrue("Africa" in airlineObj.contList)
        self.assertTrue("Australia" in airlineObj.contList)
    
    

    '''
    A quick test to make sure that the cityops for the smallest population
    are the values they should be (determined via eyeball test
    '''
    def testCitySmall(self):
        minPop = airlineObj.minCityPop
        minPopName = airlineObj.minCityName
        self.assertEquals(minPop, 589900)
        self.assertEquals(minPopName, "Essen")

    '''
    Tests the same function to make sure the largest population
    is what the JSON data dictates
    '''
    def testCityBig(self):
        maxPop = airlineObj.maxCityPop
        maxPopName = airlineObj.maxCityName
        self.assertEquals(maxPop, 34000000)
        self.assertEquals(maxPopName, "Tokyo")
    
    '''
    Tests to make sure that the average population is what
    the JSON data dictates
    '''
    def testCityAvg(self):
        avgPop = airlineObj.avgCityPop
        self.assertAlmostEquals(avgPop, 11796143)
        
    '''
    Tests to make sure the longest flight is stored to be what it should be
    '''
    def testLongFlight(self):
        longFlight = airlineObj.longestFlight
        self.assertEquals(longFlight, 12051)

    '''
    Tests to make sure the shortest flight is what it should be
    '''
    def testShortFlight(self):
        shortFlight = airlineObj.shortestFlight
        self.assertEquals(shortFlight, 132)

    '''
    Tests to make sure the average flight value is what it should be
    '''
    def testAvgFlight(self):
        avgFlight = airlineObj.avgFlight
        self.assertAlmostEquals(avgFlight, 2182)
    
    '''
    Makes sure the busy city list has what it should given the JSON data
    '''
    def testBusyCity(self):
        counter = 0
        for d in airlineObj.busyCity:
            if d.name == "Istanbul":
                counter+=1
            if d.name == "Hong Kong":
                counter +=1
        self.assertEqual(counter, 2)
    
    '''
    A quick test to make sure that the airports being stored are the 
    correct quantity
    '''
    def testAirportDic(self):
        airportSize = 48
        self.assertEquals(airportSize, len(airlineObj.airportDic))

    '''
    Tests to make sure there is the correct amount of keys in the adjacency list
    '''
    def testAdjKeys(self):
        adjSize = 48 
        self.assertEquals(adjSize, len(airlineObj.adjList))
        totSize = 142
        counter = 0
        for iterator in airlineObj.adjList:
            counter += len(airlineObj.adjList[iterator])
        self.assertEquals(totSize, counter)
    
    '''
    Tests to make sure the second JSON has what it should
    '''
    def testNewJSON(self):
        newFile = 'https://wiki.engr.illinois.edu/download/attachments/207295743/cmi_hub.json?version=1&modificationDate=1349245386000'
        newAirline = Airline()
        newAirline.airlineInit(newAirline.airportDic, newFile)
        newAirline.adjInit(newAirline.adjList, newAirline.adjOneWay, newFile)
        newAirline.setSources(newAirline.sources, newFile)
        newAirline.getDists()
        newAirline.cityOps()
        self.assertEquals(len(newAirline.airportDic), 1)
        self.assertEquals(len(newAirline.adjList), 10)
        
    '''
    Tests the city and population data of the new JSON
    '''
    def testDataNew(self):
        newFile = 'https://wiki.engr.illinois.edu/download/attachments/207295743/cmi_hub.json?version=1&modificationDate=1349245386000'
        newAirline = Airline()
        newAirline.airlineInit(newAirline.airportDic, newFile)
        newAirline.adjInit(newAirline.adjList, newAirline.adjOneWay, newFile)
        newAirline.setSources(newAirline.sources, newFile)
        newAirline.getDists()
        newAirline.cityOps()
        self.assertEquals(newAirline.longestFlight, 1836)
        self.assertEquals(newAirline.shortestFlight, 132)
        self.assertEquals(newAirline.avgCityPop, 226000)

    '''
    Mixes in the new file with the old one and makes sure things aren't screwy
    '''
    def testIntegratedAirline(self):
        newFile = 'https://wiki.engr.illinois.edu/download/attachments/207295743/cmi_hub.json?version=1&modificationDate=1349245386000'
        airlineObj.airlineInit(airlineObj.airportDic, newFile)
        airlineObj.adjInit(airlineObj.adjList, airlineObj.adjOneWay, newFile)
        airlineObj.setSources(airlineObj.sources, newFile)
        airlineObj.getDists()
        airlineObj.cityOps()
        self.assertEquals(49, len(airlineObj.airportDic))
        self.assertNotEquals(2182, airlineObj.avgFlight)
        self.assertTrue(airlineObj.shortestFlight == 132)
    
    '''
    Tests the remove city function
    '''
    def testRemoveCity(self):
        oldSize = 49
        airlineObj.update.removeCity('BKK', airlineObj)
        self.assertEquals(len(airlineObj.airportDic), 48)

    '''
    Tests the removal of a route
    '''
    def testRemoveRoute(self):
        oldSize = len(airlineObj.adjList['DEL'])
        airlineObj.update.removeRoute('DEL', 'THR', airlineObj)
        self.assertEquals(len(airlineObj.adjList['DEL']), oldSize-1)

        
        
if __name__ == "__main__":
    unittest.main()