'''
Created on Oct 2, 2012
The main function, creates an airline object as well as subsequent airpoorts, a UI, and
runs the ui to get/show the data
@author: Jeff
'''
import Code.Airport
from Code.Airline import Airline
import cProfile
from Code.UserInterface import UserInterface
from Code.UpdateAirport import UpdateAirport
import webbrowser
import urllib
'''
https://wiki.engr.illinois.edu/download/attachments/207292263/map_data.json?version=1&modificationDate=1348635625000
https://wiki.engr.illinois.edu/download/attachments/207295743/cmi_hub.json?version=1&modificationDate=1349245386000
'''
if __name__ == '__main__':
    '''
    Does the UI and airline creation, and runs the UI
    '''
    airlineObj = Airline()
    simpleUI = UserInterface(airlineObj)
    file = str(raw_input('Please enter the url or filepath of the JSON file to load'))
    while True:
        airlineObj.airlineInit(airlineObj.airportDic, file)
        airlineObj.adjInit(airlineObj.adjList, airlineObj.adjOneWay, file)
        airlineObj.setSources(airlineObj.sources, file)
        airlineObj.getDists()
        airlineObj.cityOps()
        file = raw_input('If you have another to enter, please enter it. If not, press q')
        if file == 'q':
            break
    while True:
        flag = simpleUI.decideInfo()
        if int(flag) == 0:
            break
        elif int(flag) > 12 or flag < 0:
            print 'invalid input'
            flag = simpleUI.decideInfo()
        if int(flag) == 1:
            simpleUI.printGenInfo()
        elif int(flag) == 2:
            UserInterface.getCityCode(simpleUI)
        elif int(flag) == 3:
            airlineObj.showContList()
            simpleUI.printContList()
        elif int(flag) == 4:
            urlString = airlineObj.getNetMap()
            webbrowser.open_new_tab(urlString)
        elif int(flag) == 5:
            tbrCity = simpleUI.pickCity()
        elif int(flag) == 6:
            simpleUI.getNewDataCode()
        elif int(flag) == 7:
            simpleUI.removeRoute()
        elif int(flag) == 8:
            simpleUI.addRoute()
        elif int(flag) == 9:
            simpleUI.editCity()
        elif int(flag) == 10:
            airlineObj.update.saveFile(airlineObj.sources, airlineObj.airportDic, airlineObj.adjOneWay)
        elif int(flag) == 11:
            simpleUI.getRoute()
        elif int(flag) == 12:
            simpleUI.getShortestPath()
    