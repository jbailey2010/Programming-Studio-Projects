'''
Created on Oct 3, 2012
Simply holds the distance of the flight and the end location
@author: Jeff
'''

class AdjCity():
    '''
    A simple class used to hold data for the adjacency list
    classdocs
    '''


    def __init__(self, distance, end):
        '''
        Only used for the adjacency list, it holds the data needed
        '''
        self.distance = distance
        self.endCity = end

        