{\rtf1\ansi\ansicpg1252\cocoartf2639
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\margl1440\margr1440\vieww11520\viewh8400\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 import datetime\
\
def calculate_arrival_time(distance, speed):\
    # Assuming speed is in meters per second and distance in meters\
    # Time = Distance / Speed\
    # Convert time to a timedelta for easy addition to current time\
    time_to_reach = distance / speed\
    return datetime.timedelta(seconds=time_to_reach)\
\
def sort_students_by_arrival_time(students, classroom_location, average_speed):\
    current_time = datetime.datetime.now()\
\
    for student in students:\
        # Calculate the distance between student's location and classroom\
        # This is a placeholder function. You need to replace it with actual distance calculation.\
        distance = calculate_distance(student['location'], classroom_location)\
        \
        # Calculate arrival time\
        arrival_time = current_time + calculate_arrival_time(distance, average_speed)\
        student['arrival_time'] = arrival_time\
\
    # Sort students by calculated arrival time\
    sorted_students = sorted(students, key=lambda x: x['arrival_time'])\
    return sorted_students\
\
# Example usage\
students = [\
    \{"name": "Alice", "location": (35.6895, 139.6917)\},  # Location format (latitude, longitude)\
    \{"name": "Bob", "location": (34.0522, 118.2437)\},\
    # Add more students\
]\
\
classroom_location = (40.7128, 74.0060)  # Example classroom location\
average_speed = 1.4  # Average walking speed in meters/second\
\
sorted_students = sort_students_by_arrival_time(students, classroom_location, average_speed)\
\
for student in sorted_students:\
    print(f"\{student['name']\} arrives at \{student['arrival_time']\}")\
}