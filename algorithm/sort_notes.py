{\rtf1\ansi\ansicpg1252\cocoartf2639
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\margl1440\margr1440\vieww11520\viewh8400\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 from datetime import datetime\
\
# Example notes data\
notes = [\
    \{"content": "Note 1", "created_at": datetime(2023, 12, 7, 10, 0)\},\
    \{"content": "Note 2", "created_at": datetime(2023, 12, 7, 9, 30)\},\
    \{"content": "Note 3", "created_at": datetime(2023, 12, 7, 11, 15)\}\
]\
\
# Function to sort notes by creation time\
def sort_notes_by_time(notes, descending=False):\
    return sorted(notes, key=lambda note: note["created_at"], reverse=descending)\
\
# Sorting notes in ascending order of creation time\
sorted_notes = sort_notes_by_time(notes)\
\
# Display sorted notes\
for note in sorted_notes:\
    print(f"Content: \{note['content']\}, Created at: \{note['created_at']\}")\
}