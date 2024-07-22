package com.example.bookmanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

class BookListFragment extends Fragment {

    private ListView bookListView;
    private BookDatabaseHelper bookDatabaseHelper;
    private ArrayList<Book> bookList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);

        bookListView = view.findViewById(R.id.book_list);
        bookDatabaseHelper = new BookDatabaseHelper(getContext());

        loadBooks();

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book selectedBook = bookList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("book", selectedBook);
                Fragment fragment = new BookDetailFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();
            }
        });

        return view;
    }

    private void loadBooks() {
        bookList = bookDatabaseHelper.getAllBooks();
        ArrayAdapter<Book> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, bookList);
        bookListView.setAdapter(adapter);
    }
}
