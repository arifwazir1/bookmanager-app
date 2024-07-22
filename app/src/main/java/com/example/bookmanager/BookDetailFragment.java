package com.example.bookmanager;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

class BookDetailFragment extends Fragment {

    private TextView titleTextView;
    private TextView authorTextView;
    private TextView descriptionTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);

        titleTextView = view.findViewById(R.id.book_title);
        authorTextView = view.findViewById(R.id.book_author);
        descriptionTextView = view.findViewById(R.id.book_description);

        if (getArguments() != null) {
            Book book = (Book) getArguments().getSerializable("book");
            if (book != null) {
                titleTextView.setText(book.getTitle());
                authorTextView.setText(book.getAuthor());
                descriptionTextView.setText(book.getDescription());
            }
        }

        return view;
    }
}
