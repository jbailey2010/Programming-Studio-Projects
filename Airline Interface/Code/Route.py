'''
Created on Oct 10, 2012
A new way to store routes
@author: Jeff
'''

class Route():

    '''
    The constructor for the new route class
    '''
    def __init__(self, startCity, endCity, distance):
        self.startCity = startCity
        self.endCity = endCity
        self.distance = distance
        self.parent = None
        self.accumDist = 0
        self.visited = False
        