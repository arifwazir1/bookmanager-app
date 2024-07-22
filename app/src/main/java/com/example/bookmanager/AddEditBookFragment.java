package com.example.bookmanager;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddEditBookFragment extends Fragment {

    private EditText titleEditText;
    private EditText authorEditText;
    private EditText descriptionEditText;
    private Button saveButton;
    private BookDatabaseHelper bookDatabaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_edit_book, container, false);

        titleEditText = view.findViewById(R.id.book_title);
        authorEditText = view.findViewById(R.id.book_author);
        descriptionEditText = view.findViewById(R.id.book_description);
        saveButton = view.findViewById(R.id.save_button);

        bookDatabaseHelper = new BookDatabaseHelper(getContext());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString().trim();
                String author = authorEditText.getText().toString().trim();
                String description = descriptionEditText.getText().toString().trim();

                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(author) || TextUtils.isEmpty(description)) {
                    Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Book book = new Book(title, author, description);
                    if (bookDatabaseHelper.addBook(book)) {
                        Toast.makeText(getContext(), "Book added successfully", Toast.LENGTH_SHORT).show();
                        getFragmentManager().beginTransaction().replace(R.id.content_frame, new BookListFragment()).commit();
                    } else {
                        Toast.makeText(getContext(), "Error adding book", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
}
