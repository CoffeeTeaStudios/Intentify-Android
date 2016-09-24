package lawonga.intentify.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import lawonga.intentify.R;

/**
 * Created by Andy on 9/24/2016.
 */
public class BuyInFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    @BindView(R.id.buyin_tokens_et) EditText buyIn;
    @BindView(R.id.buyin_tokens_btn) Button confirmBuyIn;
    @BindView(R.id.buyin_reload_btn) Button reloadBuyIn;

    public static BuyInFragment getInstance() {
        BuyInFragment buyInFragment = new BuyInFragment();
        return buyInFragment;
    }

    // Required empty public constructor
    public BuyInFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        confirmBuyIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Firebase confirmBuyIn

                getActivity().onBackPressed();
            }
        });

        reloadBuyIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Transition to confirmBuyInButton

                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onReloadPressed(Uri uri);
    }
}
