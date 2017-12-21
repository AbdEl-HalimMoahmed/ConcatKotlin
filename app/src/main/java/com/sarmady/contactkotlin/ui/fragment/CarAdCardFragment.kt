package com.sarmady.contactkotlin.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sarmady.contactkotlin.App
import com.sarmady.contactkotlin.R
import com.sarmady.contactkotlin.domain.entities.Car
import com.sarmady.contactkotlin.ui.util.loadUrl
import com.sarmady.contactkotlin.ui.util.setLayoutDirection
import kotlinx.android.synthetic.main.adapter_card_ad.*


class CarAdCardFragment : Fragment() {

    private lateinit var car: Car

    companion object {
        private val BUNDLE_CAR_KEY = "article"

        fun getInstance(car: Car): CarAdCardFragment {
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_CAR_KEY, car)
            val fragment = CarAdCardFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        car = arguments?.getParcelable<Car>(BUNDLE_CAR_KEY) as Car
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.adapter_card_ad, container, false)
                .setLayoutDirection(App.instance.language)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adImage.loadUrl(car.images?.get(0)?.large)
        title.text = getString(R.string.car_title, car.makeName, car.modelName)
        price.text = getString(R.string.car_price, car.price)
    }
}