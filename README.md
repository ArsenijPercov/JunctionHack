# Smooth Luggage

## Considerations

~ In 2019 travelers checked in 4.27 billion luggage, 24.8 million lost

~ Passengers are not informed up-to-date where their luggage is

## Solution

~ Provide passengers with Android application where they can track
their luggage

~ Data is analyzed and parsed based on the baggageId and is sent to a
dynamic database Firebase, such that changes could be implemented
at real time and notifications to passengers are sent directly

~ Once a user enters a baggageId, it is added to the local storage of
their device and the baggageId ’listens’ for new events in the dynamic
database, until the status is set to claimed

~ The application is going to store the information for a particular
luggage up to 7 days once it is marked as successfully claimed

~ In case of mishandled luggage, the application does not clean the data of the baggageId

## Room for Improvement

~ By default, on every stage, the passenger receives a notification

~ User accounts
