package com.bignerdranch.android.escapeovatortemp.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.escapeovatortemp.Note;
import com.bignerdranch.android.escapeovatortemp.database.NoteDBSchema.NoteTable;

import java.util.UUID;

/**
 * A CursorWrapper that reads in Notes from the database
 */
public class NoteCursorWrapper extends CursorWrapper
{
    // Constructor
    public NoteCursorWrapper(Cursor cursor)
    {
        super(cursor);
    }

    // Reads in a note from the database
    public Note getNote()
    {
        String uuidString = getString(getColumnIndex(NoteTable.Cols.UUID));
        int floorNumber = getInt(getColumnIndex(NoteTable.Cols.FLOOR_NUMBER));
        String noteText = getString(getColumnIndex(NoteTable.Cols.NOTE_TEXT));
        int isEditable = getInt(getColumnIndex(NoteTable.Cols.EDITABLE));
        byte[] image = getBlob(getColumnIndex(NoteTable.Cols.IMAGE));

        return new Note(UUID.fromString(uuidString), floorNumber, noteText, isEditable, image);
    }
}
