package com.android2ee.recyclerview.itemanimator;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;

/**
 * 
 * @author florian
 * Class FlipInAnimator 
 * Provides an animation with flip in top 
 * for a RecyclerView
 *
 */
public class FlipInAnimator extends PendingItemAnimator {

    @Override
    protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
    	// start animate rotationX 0 --> 90
        ViewCompat.animate(holder.itemView)
                .rotationX(90)
                .setDuration(getRemoveDuration())
                .setListener(new DefaultRemoveVpaListener(holder))
                .start();
        // Add holder in mRemoveAnimations
        mRemoveAnimations.add(holder);
    }

    @Override
    protected void preAnimateAdd(RecyclerView.ViewHolder holder) {
    	// prepare animate 
        ViewCompat.setRotationX(holder.itemView, 90);
    }

    @Override
    protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
    	// start animate rotationX 90 -> 0
        ViewCompat.animate(holder.itemView)
                .rotationX(0)
                .setDuration(getAddDuration())
                .setListener(new DefaultAddVpaListener(holder)).start();
        // Add holder in mAddAnimations
        mAddAnimations.add(holder);
    }
}
