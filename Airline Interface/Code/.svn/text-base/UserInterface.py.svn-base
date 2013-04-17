'''
Created on Oct 5, 2012
Holds the userinterface class, which just gets the user input
@author: Jeff
'''
import math
import Airport
class UserInterface():


    def __init__(self, airPort):
        '''
        Sets the basic member variables, citycode for the soon user input
        and airport to make function calls simpler later
        '''
        self.cityCode = None
        self.airPort = airPort
    

    def decideInfo(self):
        '''
        Prompts the user to decide what he wants the interface to show
        '''
        print 'Press 0 to quit'
        print 'Press 1 if you would like general information about CSAir'
        print 'Press 2 if you would like general information about a city'
        print 'Press 3 if you would like to see a list of the continents served by CSAir'
        print 'Press 4 if you would like to view a map of the CSAir network'
        print 'Press 5 if you would like to remove an airport from the network'
        print 'Press 6 if you would like to add a city to the system'
        print 'Press 7 if you would like to remove a route'
        print 'Press 8 if you would like to add a route'
        print 'Press 9 if you would like to edit an existing city'
        print 'Press 10 if you would like to save the new data to a file'
        print 'Press 11 if you would like to get information about a route'
        print 'Press 12 to find the shortest path between two cities in the network'
        flag = raw_input()
        return flag
    

    def getCityCode(self):
        '''
        Prompts the user to give a city code
        This fails and calls itself if the user inputs an invalid key
        '''
        cityCode = raw_input('Please enter the code of the city about which you would like information')
        if cityCode in self.airPort.airportDic:
            self.cityCode = cityCode
            self.airPort.airportDic[cityCode].printCityInfo()
            while True:
                flag = raw_input('Press r to return or q to quit')
                if flag == 'r':
                    return
                elif flag == 'q':
                    exit()
        else:
            print 'Invalid key'
            self.getCityCode()
    
   
    def printContList(self):
        '''
        This prints the continent list set up eariler in airport
        '''
        airportList = self.airPort.contList
        for cont in airportList:
            print cont, 'holds hubs in:'
            for city in airportList[cont]:
                print city
        while True:
            flag = raw_input('Press r to return or q to quit')
            if flag == 'r':
                return
            elif flag == 'q':
                exit()


    def printGenInfo(self):
        '''
        Prints the general information about the airport
        '''
        while True:
            print 'The longest single flight on the network is', self.airPort.longestFlight, 'Miles'
            print 'The shortest single flight on the network is', self.airPort.shortestFlight, 'Miles'
            print 'The average flight length on the network is', self.airPort.avgFlight, 'Miles'
            print 'The largest city flown to is', self.airPort.maxCityName, 'with a population of', self.airPort.maxCityPop
            print 'The smallest city flown to is', self.airPort.minCityName, 'with a population of', self.airPort.minCityPop
            print 'The average city size flown to is', self.airPort.avgCityPop
            print 'The busiest hubs in the CSAir network are:'
            for cities in self.airPort.busyCity:
                print cities.name
            flag = raw_input('Press r to return or q to quit')
            if flag == 'r':
                return
            elif flag == 'q':
                exit()
                

    def pickCity(self):
        '''
        Very simple function, just gets the code of the city the user wishes to removes
        and passes it to the airport function to remove it
        '''
        cityCode = raw_input('Please enter the code of the city you would like to remove')
        if cityCode in self.airPort.airportDic:
            self.airPort.update.removeCity(cityCode, self.airPort)
            print 'City removed, city and flight data updated accordingly'
            while True:
                flag = raw_input('Press r to return or q to quit')
                if flag == 'r':
                    return
                elif flag == 'q':
                    exit()
        else:
            print 'Invalid key'
            self.pickCity()
    

    def getNewDataCode(self):
        '''
        Proceeds to get all of the data to make a new airport, getting all of the data from the user
        '''
        newPort = Airport.Airport(None, None, None, None, None, None, None, None)
        print 'What city code would you like to give this airport? Press r to return'
        code = raw_input()
        if code == 'r':
            return
        if code in self.airPort.airportDic:
            print 'Already in the database, please enter a valid input'
            self.getNewDataCode()
        newPort.code = code
        self.getNewName(newPort)
        

    def getNewName(self, newPort):
        '''
        A simple little function that gets the name of the new city
        '''
        print 'Please enter the name you want to give this city and the population, separated by a comma, or press r to return'
        name = raw_input()
        if name == 'r':
            return
        while len(name) == 0:
            print 'Please enter a name'
            name = raw_input()
        while ',' not in name:
            print 'Please enter all of the data'
            name = raw_input
        index = name.index(',')
        newPort.name = name[:index]
        pop = name[(index+1):]
        while pop.isdigit() == False:
            print 'Please enter an integer for population'
            pop = raw_input()
        newPort.population = int(pop)
        self.getCountCont(newPort)
        

    def getCountCont(self, newPort):
        '''
        A function that sets the continent and country of the new airport
        '''
        print 'Please enter the country and continent of this airport, separated by a comma, or r to return'
        location = raw_input()
        if location == 'r':
            return
        while len(location) == 0:
            print 'Please enter a location'
            location = raw_input()
        index = location.index(',')  
        country = location[:index]
        continent = location[(index+1):]     
        newPort.country = country
        newPort.continent = continent
        self.getTimePopReg(newPort)
        

    def getTimePopReg(self, newPort):
        '''
        Simply gets the timezone and region of the new city
        '''
        print 'Please enter the timezone and region of the new city, separated by only a comma, or r to return'
        data = raw_input()
        if data == 'r':
            return
        while len(data) == 0:
            print 'Please enter the data'
            data = raw_input()
        index = data.index(',')
        timez = data[:index]
        while timez.isdigit() == False:
            print 'Invalid timezone, please enter a number'
            timez = raw_input()
        newPort.timezone = int(timez)
        region = data[(index + 1):]
        while region.isdigit() == False:
            print 'Invalid region, please enter a number'
            region = raw_input()
        region = int(data[(index+1):])
        newPort.region = region
        self.getCoords(newPort)
    

    def getCoords(self, newPort):
        '''
        Gets the coordinates, adds this to the airportDic, and re-caluclates data
        '''
        print 'Please enter the coordinates of the new city, separated by only a comma, or r to return'
        data = raw_input()
        if data == 'r':
            return
        while len(data) == 0:
            print 'Please enter the data'
            data = raw_input()
        index = data.index(',')
        x = data[:index]
        y = data[(index + 1):]
        while x.isdigit() == False:
            print 'Please enter a valid integer for the x coordinate'
            x = raw_input()
        while y.isdigit() == False:
            print 'Please enter a valid integer for the y coordinate'
            y = raw_input
        xCoord = int(x)
        yCoord = int(y)
        newPort.coordinates = {xCoord, yCoord}
        self.airPort.update.reCalc(newPort, self.airPort)
        print 'City added, city and flight data updated accordingly'
        while True:
            flag = raw_input('Press r to return or q to quit')
            if flag == 'r':
                return
            elif flag == 'q':
                exit()
    
    def removeRoute(self):
        '''
        Removes a route specified by the user
        '''
        print 'Please enter the starting city code'
        startCity = raw_input()
        while len(startCity) == 0:
            print 'Please enter the data'
            startCity = raw_input()
        while startCity not in self.airPort.adjList:
            print 'Please enter valid data'
            startCity = raw_input()
        print 'Please enter the ending city code'
        endCity = raw_input()
        while len(endCity) == 0:
            print 'Please enter the data'
            endCity = raw_input()     
        while endCity not in self.airPort.adjList:
            print 'Please enter valid data'
            endCity = raw_input()      
        self.airPort.update.removeRoute(startCity, endCity, self.airPort)
        print 'Route removed, distance functions updated accordingly'
        while True:
            flag = raw_input('Press r to return or q to quit')
            if flag == 'r':
                return
            elif flag == 'q':
                exit()            
    
    def addRoute(self):
        '''
        Gets the user's data to add a route to the database
        '''
        print 'Please enter the starting city code of this route'
        startCity = raw_input()
        while len(startCity) == 0:
            print 'Please enter the data'
            startCity = raw_input()
        while startCity not in self.airPort.adjList:
            print 'Please enter a code already in the system'
            startCity = raw_input()
        print 'Please enter the ending city code'
        endCity = raw_input()
        while len(endCity) == 0:
            print 'Please enter the data'
            endCity = raw_input()     
        while endCity not in self.airPort.adjList:
            print 'Please enter a code already in the system'
            endCity = raw_input()
        if endCity in self.airPort.adjList[startCity]:
            print 'This is already a route, please enter one that is not already connected'
            self.addRoute()
        distance = 0
        while distance < 1:
            distance = int(raw_input('Please enter the distance of this route'))
        self.airPort.update.addRoute(startCity, endCity, self.airPort, distance)
        print 'Route added, distance functions updated accordingly'
        while True:
            flag = raw_input('Press r to return or q to quit')
            if flag == 'r':
                return
            elif flag == 'q':  
                exit()      
    
    def editCity(self):
        '''
        Gets the user's preferred city and edits whatever parts he/she wants
        '''
        cityCode = raw_input('Please enter the code of the city you would like to edit')
        if cityCode not in self.airPort.airportDic:
            print 'Invalid input, not an existing city code'
            self.editCity()
        print 'Press 1 if you would like to edit the name'
        print 'Press 2 if you would like to edit the country'
        print 'Press 3 if you would like to edit the continent'
        print 'Press 4 if you would like to edit the population'
        print 'Press 5 if you would like to edit the timezone'
        print 'Press 6 if you would like to edit the region'
        print 'Press 7 if you would like to edit the coordinates'
        flag = int(raw_input())
        self.airPort.update.editCity(flag, self.airPort, cityCode)
        print 'City updated, press r to return, q to quit, or u to continue updating the city'
        while True:
            flag = raw_input()
            if flag == 'r':
                return
            elif flag == 'q':  
                exit()  
            elif flag == 'u':
                self.editCity()
    
    def getRoute(self):
        '''
        Gets the route from the user, validated here as well
        '''
        print 'Please enter the route about which you would like information (in the format HKG-BKK...etc)'
        route = raw_input()
        if '-' not in route:
            print 'Please enter a series of codes, separated by a -'
            self.getRoute()
        route = route.split('-')
        index = 0
        secIndex = 1
        for routes in self.airPort.adjList[route[index]]:
            if route[secIndex] in routes.endCity:
                secIndex = 0
                break
        if secIndex != 0:
            print 'The route is invalid, please enter a valid route'
            self.getRoute()
        print 'The route is valid. What would you like to know about it?'
        self.getInfo(route)
    
    def getInfo(self, route):
        '''
        Gets what the user wants to know about the input route
        '''
        print 'Press 1 if you want to know the total distance'
        print 'Press 2 if you want to know the cost of the route'
        print 'Press 3 if you want to know the time of the route'
        print 'Press r to return'
        flag = raw_input()
        if flag == 'r':
            return
        elif int(flag) == 1:
            self.getTotalDistance(route)
        elif int(flag) == 2:
            self.getCost(route)
        elif int(flag) == 3:
            self.getTime(route)
    
    def getTotalDistance(self, route):
        '''
        Calculates the total distance of a route
        '''
        index = 0
        secIndex = 1
        distance = 0
        while secIndex < len(route):
            startCity = route[index]
            endCity = route[secIndex]
            print startCity, endCity
            for routes in self.airPort.adjList[startCity]:
                print 'routes', routes.startCity, routes.endCity
                if routes.startCity == startCity and routes.endCity == endCity:
                    distance += routes.distance
            index += 1
            secIndex += 1
        print 'The total distance of this route is', distance
        while True:
            flag = raw_input('Press r to return, q to quit, or u to keep getting information')
            if flag == 'r':
                return
            elif flag == 'q':
                exit()
            elif flag == 'u':
                self.getInfo(route)
    
    def getCost(self, route):
        '''
        Calculates the total Cost of the inputted route
        '''
        totalCost = 0
        perKM = (float)(0.35)
        index = 0
        secIndex = 1
        distance = 0
        while secIndex < len(route):
            startCity = route[index]
            endCity = route[secIndex]
            for routes in self.airPort.adjList[startCity]:
                if routes.startCity == startCity and routes.endCity == endCity:
                    distance = routes.distance
                    totalCost += float(distance*perKM)
            index += 1
            secIndex += 1
            perKM -= 0.05
        print 'The total cost is', totalCost
        while True:
            flag = raw_input('Press r to return, q to quit, or u to keep getting information')
            if flag == 'r':
                return
            elif flag == 'q':
                exit()
            elif flag == 'u':
                self.getInfo(route)
    
    def getTime(self, route):
        '''
        Calculates the total time of a trip
        '''
        index = 0
        secIndex = 1
        time = (float)(0.0)
        last = len(route)-1
        layover = (float)(2.0)
        accel = (float)(1406.25)
        while secIndex < len(route):
            startCity = route[index]
            endCity = route[secIndex]
            if index != 0 and secIndex != last:
                size = len(self.airPort.adjList[startCity]) - 1
                layover -= size/6.0
                time += layover
            for routes in self.airPort.adjList[startCity]:
                if routes.startCity == startCity and routes.endCity == endCity:
                    distance = routes.distance
                    if distance < 400:
                        halfDist = distance / 2
                        halfDist *= 2
                        halfDist = halfDist / 1406.25
                        halfDist = math.sqrt(halfDist)
                        halfDist *= 2
                        time += halfDist
                    else:
                        startAccel = 400
                        startAccel = startAccel/accel
                        startAccel = math.sqrt(startAccel)
                        startAccel *= 2
                        time += startAccel
                        print time
                        remDist = distance - 400
                        remDist = remDist / 750.0
                        time += remDist
                        print time
            index += 1
            secIndex += 1   
        print time    
        print 'The total time is', time
        while True:
            flag = raw_input('Press r to return, q to quit, or u to keep getting information')
            if flag == 'r':
                return
            elif flag == 'q':
                exit()
            elif flag == 'u':
                self.getInfo(route)
    
    def getShortestPath(self):
        '''
        Get the start and end cities from the user
        '''
        startEnd = raw_input('Please enter the starting and ending cities, separated by a -')
        start = startEnd.split('-')
        startCity = start[0]
        endCity = start[1]
        self.airPort.update.dijkstraAlg(startCity, endCity, self.airPort.adjList)
        while True:
            flag = raw_input('Press r to return or q to quit')
            if flag == 'r':
                return
            elif flag == 'q':
                exit()
       