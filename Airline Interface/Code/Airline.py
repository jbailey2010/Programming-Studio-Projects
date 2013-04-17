'''
Created on Oct 2, 2012
Holds the airline object functions
@author: Jeff
'''
import json
import urllib2
from Airport import Airport
from AdjCity import AdjCity
from Route import Route
from UpdateAirport import UpdateAirport
import operator
import webbrowser


class Airline:
    '''
    Serves as the parent object for the graph, holding much of the data 
    and adjacency list and list of nodes
    '''  
    def __init__(self):
        self.airportDic = {}
        self.adjList = {} 
        self.adjOneWay = {}
        self.update = UpdateAirport()
        self.maxCityPop = 0
        self.maxCityName = None
        self.minCityPop = 1000000000
        self.minCityName = None
        self.avgCityPop = 0
        self.longestFlight = 0
        self.shortestFlight = 1000000000
        self.avgFlight = 0
        self.flightCt = 0
        self.cityCt = 0
        self.maxPorts = 0
        self.busyCity = {}
        self.contList = {}
        self.sources = {}    

    def airlineInit(self, newAirline, url):
        '''
        Initializes the airline to hold the dictionary of airports after loading the
        json data
        '''
        jsonData = urllib2.urlopen(url)
        data = json.load(jsonData)  
        for metro in data["metros"]:
            newPort = Airport(metro["code"], metro["name"], metro["country"], metro["continent"],
                          metro["timezone"], metro["coordinates"], metro["population"],
                          metro["region"])
            newAirline[metro["code"]] = newPort

            
    def setSources(self, dataSources, url):
        jsonData = urllib2.urlopen(url)
        data = json.load(jsonData)  
        if len(dataSources) == 0:
            dataSources = data["data sources"]
        else:
            for url in data["data sources"]:
                dataSources.append(url)

    def adjInit(self, adjList, adjOneWay, url):
        '''
        Initializes the adjacency list to hold all of the adjacent airports of each
        airport
        '''
        jsonData = urllib2.urlopen(url)
        data = json.load(jsonData)  
        for routes in data["routes"]:
            portSE = routes["ports"]
            start = portSE[0]
            end = portSE[1]
            dist = routes["distance"]
            if start not in adjList:
                adjList[start] = []
                adjList[start] = [Route(start, end, dist)]
            elif start not in adjOneWay:
                adjOneWay[start] = []
                adjOneWay[start] = [Route(start, end, dist)]
            elif start in adjList:
                adjList[start].append(Route(start, end, dist))
            elif start in adjOneWay:
                adjOneWay[start].append(Route(start, end, dist))
            if end not in adjList:
                    adjList[end] = []
                    adjList[end] = [Route(end, start, dist)]
            elif end in adjList:
                    adjList[end].append(Route(end, start, dist))
        

    
   
    def getDists(self):
        '''
        Runs one iteration through the airport dictionary to set all of the distance
        variables
        ''' 
        for start in self.adjList:
            size = len(self.adjList[start])
            if size == self.maxPorts:
                self.busyCity.append(self.airportDic[start])
            elif size > self.maxPorts:
                self.maxPorts = size
                if len(self.busyCity) == 0:
                    self.busyCity = [self.airportDic[start]]
                else:
                    del(self.busyCity)
                    self.busyCity = [self.airportDic[start]]
            for distCheck in self.adjList[start]:
                if distCheck.distance < self.shortestFlight:
                    self.shortestFlight = distCheck.distance
                if distCheck.distance > self.longestFlight:
                    self.longestFlight = distCheck.distance
                self.avgFlight += distCheck.distance
                self.flightCt += 1
        self.avgFlight = self.avgFlight / self.flightCt


    def cityOps(self):
        '''
        Runs one iteration through the dictionary to get all of the
        population data and store it
        '''
        for name in self.airportDic:
            population = self.airportDic[name].population
            if population < self.minCityPop:
                self.minCityPop = population
                self.minCityName = self.airportDic[name].name
            if population > self.maxCityPop:
                self.maxCityPop = population
                self.maxCityName = self.airportDic[name].name
            self.avgCityPop += population
            self.cityCt += 1
        self.avgCityPop = self.avgCityPop / self.cityCt

        
    def getNetMap(self):
        '''
        Runs the map function, getting all of the connections and making
        a URL out of it
        '''
        urlStart = "http://www.gcmap.com/mapui?P="
        for startCity in self.adjList:
            for endCity in self.adjList[startCity]:
                urlStart = urlStart + '+'
                urlStart = urlStart + startCity + "-" + endCity.endCity + ","
        return urlStart
    

    def showContList(self):
        '''
        Sets the continent list to hold all of the cities in each
        '''
        for cities in self.airportDic:
            cont = self.airportDic[cities].continent
            if cont not in self.contList:
                self.contList[cont] = []
                self.contList[cont] = [self.airportDic[cities].name]
            elif cont in self.contList:
                self.contList[cont].append(self.airportDic[cities].name)
    


