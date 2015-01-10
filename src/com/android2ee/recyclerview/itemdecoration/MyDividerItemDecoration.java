package com.android2ee.recyclerview.itemdecoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 
 * @author florian
 * Class MyDividerItemDecoration
 * Provides an separator between row or colon in RecyclerView
 *
 */
public class MyDividerItemDecoration extends RecyclerView.ItemDecoration {
	 
	/**
	 * Attributes of separator  android.R.attr.listDivider
	 */
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
 
    // static HORIZONTAL_LIST
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    // static VERTICAL_LIST
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
 
    /**
     * The drawable of separator to display
     */
    private Drawable mDivider;
 
    /**
     * The orientation of recyclerView HORIZONTAL_LIST, VERTICAL_LIST
     */
    private int mOrientation;
 
    /**
     * Constructor MyDividerItemDecoration
     * @param context : the context
     * @param orientation : the orientation HORIZONTAL_LIST, VERTICAL_LIST
     */
    public MyDividerItemDecoration(Context context, int orientation) {
    	// get the attributes style
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        // get drawable in style
        mDivider = a.getDrawable(0);
        a.recycle();
        // set orientation
        setOrientation(orientation);
    }
 
    /**
     * Set Orientation
     * @param orientation : the new orientation HORIZONTAL_LIST or VERTICAL_LIST
     */
    public void setOrientation(int orientation) {
    	// if not HORIZONTAL_LIST or VERTICAL_LIST , throw an exception
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }
 
    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
    	// call the right draw depends the orientation
        if (mOrientation == VERTICAL_LIST) {
        	// vertical
            drawVertical(c, parent);
        } else {
        	// horizontal
            drawHorizontal(c, parent);
        }
    }
 
    /**
     * Draw Vertical, so horizontal separator
     * @param c : the canvas
     * @param parent : the view parent
     */
    public void drawVertical(Canvas c, RecyclerView parent) {
    	// get padding of parents (Left and right
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        
        // get the count of child
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            // create layoutParams
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            // calculate new padding child
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            // set bounds
            mDivider.setBounds(left, top, right, bottom);
            // draw in canvas
            mDivider.draw(c);
        }
    }
 
    /**
     * Draw Horizontal, so vertical separator
     * @param c : the canvas
     * @param parent : the view parent
     */
    public void drawHorizontal(Canvas c, RecyclerView parent) {
    	// get padding of parents (Top and Bottom
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
 
        // get the count of child
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            // create layoutParams
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            // calculate new padding child
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
      	  	// set bounds
            mDivider.setBounds(left, top, right, bottom);
            // draw in canvas
            mDivider.draw(c);
        }
    }
 
    /**
     * Return the dimension outRect for itemPosition and parent (Offset)
     * @param outRect : the new Rect
     * @param itemPosition: the position of item
     * @param parent : the view parent
     * 
     */
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
    	// depends orientation
        if (mOrientation == VERTICAL_LIST) {
        	// return rect with height of divider
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
        	// return rect with width of divider
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }
}
