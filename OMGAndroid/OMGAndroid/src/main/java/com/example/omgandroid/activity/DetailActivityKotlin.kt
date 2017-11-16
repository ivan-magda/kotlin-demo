package com.example.omgandroid.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.ShareActionProvider
import com.example.omgandroid.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivityKotlin : Activity() {

    private val IMAGE_URL_BASE = "http://covers.openlibrary.org/b/id/"
    private var mImageURL = ""
    private var mShareActionProvider: ShareActionProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        actionBar?.setDisplayHomeAsUpEnabled(true)

        val coverId = this.intent.extras.getString("coverID")
        val length = coverId?.length ?: 0

        if (length > 0) {
            mImageURL = IMAGE_URL_BASE + coverId + "-L.jpg"
            Picasso.with(this)
                    .load(mImageURL)
                    .placeholder(R.drawable.img_books_loading)
                    .into(img_cover)
        }
    }

    private fun setShareIntent() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Book Recommendation!")
        shareIntent.putExtra(Intent.EXTRA_TEXT, mImageURL)

        mShareActionProvider?.setShareIntent(shareIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val shareItem = menu.findItem(R.id.menu_item_share)
        mShareActionProvider = shareItem!!.actionProvider as ShareActionProvider

        setShareIntent()

        return true
    }
}
