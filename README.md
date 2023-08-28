# ActivityPlanner
SpringBoot Application that uses PostgreSQL as database and helps in planning activites for passenger

* HLD/LLD and necessary information provided in Nymble.pdf

Following are the requirements handled in this application:

* Passenger will be able to select a travel package
* Travel package would have destination
* Destination would have a list of activities
* Passenger would be able to sign up for activity/list of activities
* Passenger’s balance would be deducted based on the passenger’s type
* Travel agency/passenger can view itinerary details
* Travel agency can add destinations

APIs to fetch the following requirements are :

* Print itinerary of the travel package including:
travel package name,
destinations and details of the activities available at each destination, like name, cost, capacity and description. -
API to get this - api/getTravelPackageItineraryDetails

* Print the passenger list of the travel package including:
package name,
passenger capacity,
number of passengers currently enrolled and
name and number of each passenger
API to get this- api/getTravelPackagePassengerDetails

* Print the details of an individual passenger including their
name,
passenger number,
balance (if applicable),
list of each activity they have signed up for, including the destination the at which the activity is taking place and the price the passenger paid for the activity.
API to get this- api/getPassengerDetails

* Print the details of all the activities that still have spaces available, including how many spaces are available.
API to get this- api/getAvailableActivities


