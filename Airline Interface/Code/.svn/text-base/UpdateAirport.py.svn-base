'''
Created on Oct 11, 2012

@author: Jeff
'''
from Route import Route
from pprint import pprint
import string
import Queue
class UpdateAirport():
    '''
    Simply holds the helper functions for the interface relative to the airline class
    '''


    def __init__(self):
        '''
        A null constructor
        '''


    def removeRoute(self, startCity, endCity, airline):
        '''
        This removes the route input by the user
        '''
        for route in airline.adjList[startCity]:
            if route.endCity == endCity:
                airline.adjList[startCity].remove(route)
        if len(airline.adjList[startCity]) == 0:
            airline.adjList.remove[startCity]
        for route in airline.adjList[endCity]:
            if route.endCity == startCity:
                airline.adjList[endCity].remove(startCity)
        if len(airline.adjList[endCity]) == 0:
            airline.adjList.remove[endCity]
        airline.getDists()

    def addRoute(self, startCity, endCity, airline, distance):
        '''
        This adds the route input by the user
        '''
        newRoute = Route(startCity, endCity, distance)
        airline.adjList[startCity].append(newRoute)
        airline.adjOneWay[startCity].append(newRoute)
        revRoute = Route(endCity, startCity, distance)
        airline.adjList[endCity].append(revRoute)
        airline.getDists()
    
    
    def reCalc(self, newPort, airline):
        '''
        This just re-calculates various distance formulas given
        the newly added city
        '''
        airline.airportDic[newPort.name] = newPort
        airline.getDists()
        airline.cityOps()
        return


    def removeCity(self, cityCode, airline):
        '''
        This function will iterate through all of the various lists and dictionaries,
        removing all instances of the city code. By removing it from the airportdic it's automatically
        removed from the continents list, and when it's removed from the adj list, it's taken from the URL as well
        '''
        for ports in airline.airportDic:
            if ports == cityCode:
                del airline.airportDic[cityCode]
                break
        for ports in airline.adjList:
            if ports == cityCode:
                del airline.adjList[cityCode]
                for iterator1 in airline.adjList:
                    for iterator2 in airline.adjList[iterator1]:
                        if iterator2.endCity == cityCode:
                            airline.adjList[iterator1].remove(iterator2)
                break
        airline.getDists()
        airline.cityOps()
    
    def editCity(self, flag, airline, cityCode):
        '''
        This simply breaks the flag down into all of the possible ultimate calls
        '''
        if flag == 1:
            self.updateName(airline, cityCode)
        elif flag == 2:
            self.updateCountry(airline, cityCode)
        elif flag == 3:
            self.updateContinent(airline, cityCode)
        elif flag == 4:
            self.updatePopulation(airline, cityCode)
        elif flag == 5:
            self.updateTime(airline, cityCode)
        elif flag == 6:
            self.updateRegion(airline, cityCode)
        elif flag == 7:
            self.updateCoords(airline, cityCode)
    
    def updateName(self, airline, cityCode):
        '''
        updates the name of the city
        '''
        oldName = airline.airportDic[cityCode].name
        print 'What would you like the name to become? The old name is', oldName
        newName = raw_input()
        while len(newName) == 0:
            newName = raw_input('Please enter a name')
        airline.airportDic[cityCode].name = newName
    
    def updateCountry(self, airline, cityCode):
        '''
        Updates the country of the city
        '''
        oldCountry = airline.airportDic[cityCode].country
        print 'What would you like to change the country to? The old country was', oldCountry
        newCountry = raw_input()
        while len(newCountry) == 0:
            newCountry = raw_input('Please enter a name')
        airline.airportDic[cityCode].country = newCountry
    
    def updateContinent(self, airline, cityCode):
        '''
        Updates the continent of the city
        '''
        oldContinent = airline.airportDic[cityCode].continent
        print 'What would you like the continent to become? The old continent was', oldContinent
        newCont = raw_input()
        while len(newCont) == 0:
            newCont = raw_input('Please enter a name')
        airline.airportDic[cityCode].continent = newCont
        
    def updatePopulation(self, airline, cityCode):
        '''
        Updates the population of the city
        '''
        oldPop = airline.airportDic[cityCode].population
        print 'What would you like the population to become? The old was', oldPop
        newPop = int(raw_input())
        while newPop < 1:
            newPop = raw_input('Please enter a valid population')
        airline.airportDic[cityCode].population = newPop
        
    def updateTime(self, airline, cityCode):
        '''
        Updates the time zone of the city
        '''
        oldTime = airline.airportDic[cityCode].timezone
        print 'What would you like the timezone to become? The old timezone was', oldTime
        newTime = int(raw_input())
        while newTime < -12 or newTime > 12:
            newTime = raw_input('Please enter a valid timezone')
        airline.airportDic[cityCode].timezone = newTime
        
    def updateRegion(self, airline, cityCode):
        '''
        Updates the region of the city
        '''
        oldReg = airline.airportDic[cityCode].region
        print 'What would you like the region to become? The old region was', oldReg
        newReg = int(raw_input())
        while newReg < 0:
            newReg = raw_input('Please enter a valid region')
        airline.airportDic[cityCode].region = newReg
        
    def updateCoords(self, airline, cityCode):
        '''
        Updates the coordinates of the city
        '''
        oldCoords = airline.airportDic[cityCode].coordinates
        print 'What would you like the coordinates to become? Please separate them by only a comma. The old were', oldCoords
        newCoords = raw_input()
        while ',' not in newCoords:
            newCoords = raw_input('Please enter a valid set')
        index = newCoords.index(',')
        xCoord = newCoords[:index]
        yCoord = newCoords[(index+1):]
        airline.airportDic[cityCode].coordinates = {xCoord, yCoord}
    
    def saveFile(self, sources, airportDic, adjList):
        '''
        Takes the user's input to save the JSON file, with the user picking the filename
        '''
        file = raw_input('Please enter the name you would like to save this file to, r to return, or q to quit')
        if file == 'r':
            return
        elif file == 'q':
            exit()
        else:
            finalDict = {"data sources" : [], "metros" : [], "routes":[]}
            file = file + ".json"
            userFile = open(file, 'w')
            
            #Makes the sources what they should be
            for source in sources:
                for url in sources[source]:
                    url = str(url)
                    finalDict["data sources"].append(url)    
                      
            #Makes the metros what they should be         
            for ports in airportDic:
                city = airportDic[ports]
                coords = {}
                for coord in city.coordinates:
                    coord = str(coord)
                    coords[coord] = city.coordinates[coord]
                cityDict = {
                'code': str(city.code),
                'name': str(city.name),
                'country': str(city.country),
                'continent': str(city.continent),
                'timezone': city.timezone,
                'coordinates': coords,
                'population': city.population,
                'region': city.region}
                finalDict["metros"].append(cityDict)
                
            #Makes the routes what they should be
            for startCities in adjList:
                for routes in adjList[startCities]:
                    port = [str(routes.startCity), str(routes.endCity)]
                    routeDict =   {
                    'ports' : port,
                    'distance': routes.distance
                    }
                    finalDict["routes"].append(routeDict)
                    
            pprint(finalDict, userFile)
            while True:
                file = raw_input('Please enter the name you would like to save this file to, r to return, or q to quit')
                if file == 'r':
                    return
                elif file == 'q':
                    exit()
            
    def dijkstraAlg(self, startCity, endCity, adjList):
        '''
        Finds the shortest path between two cities
        '''
        pQueue = Queue.PriorityQueue()
        for adj in adjList[startCity]:
            adj.accumDist = adj.distance
            pQueue.put((adj.accumDist, adj))
        for iter in adjList:
            for adjC in adjList[iter]:
                if adjC.startCity == startCity:
                    adjC.accumDist = adj.distance
                    newAdj = Route(adjC.endCity, adjC.startCity, adjC.accumDist)
                    pQueue.put((adjC.accumDist, newAdj))
        itEdge = pQueue.get()
        adj = itEdge[1]
        while adj.endCity != endCity:
            itEdge[1].visited = True
            for adj in adjList[itEdge[1].endCity]:
                adj.parent = itEdge[1]
                if adj.visited == False:
                    adj.accumDist = adj.parent.accumDist + adj.distance
                    if adj.endCity == endCity:
                        break
                    for iter in adjList[adj.endCity]:
                        if iter.accumDist < adj.accumDist and iter.visited == True:
                            adj.parent = iter
                    pQueue.put((adj.accumDist, adj))
            for iter in adjList:
                for adjC in adjList[iter]:
                    if adjC.endCity == itEdge[1].endCity:
                        newAdj = Route(adjC.endCity, adjC.startCity, adjC.distance)
                        newAdj.parent = itEdge[1]
                        if newAdj.visited == False:
                            newAdj.accumDist = newAdj.distance + newAdj.parent.accumDist
                            if newAdj.startCity == endCity:
                                adj = newAdj
                                break
                            for iter in adjList[newAdj.endCity]:
                                if iter.accumDist < newAdj.accumDist and iter.visited == True:
                                    newAdj.parent = iter 
                            pQueue.put((adjC.accumDist, newAdj))  
  
            itEdge = pQueue.get()
        print 'Done! The path is:'
        print adj.endCity
        print adj.startCity
        while adj.startCity != startCity:
            adj = adj.parent
            print adj.endCity
            print adj.startCity


        
        
                

        