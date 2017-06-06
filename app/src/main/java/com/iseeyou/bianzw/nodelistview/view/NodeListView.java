package com.iseeyou.bianzw.nodelistview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.iseeyou.bianzw.nodelistview.R;
import com.iseeyou.bianzw.nodelistview.bean.NodeBean;

import java.util.List;

/**
 * 作者：張利军 on 2017/6/3 0003 15:34
 * 邮箱：282384507@qq.com
 * 公司：南京艾思优信息科技有限公司
 */
public class NodeListView extends ListView {
    //画笔
    private Paint mPaint;
    //list集合
    private List<Object> mDatas;
    //特定的适配器
    private SpecificAdapter mAdapter;

    public NodeListView(Context context) {
        super(context);
        init();
    }

    public NodeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NodeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 让ListView不能滑动
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //让ListView不能滑动
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    /**
     * 初始化
     */
    private void init() {
        //去掉分割线
        setDivider(null);
        //初始化适配器
        mAdapter = new SpecificAdapter();
        //设置adapter
        setAdapter(mAdapter);
        //初始化画笔
        mPaint = new Paint();
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        //绘制完成之后划线  判断是否为null以及是否为零
        if (mAdapter != null && mAdapter.getCount() != 0) {
            drawLine(canvas);
            drawPoint(canvas);
        }
    }


    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
    }

    /**
     * 绘制完成之后划线
     *
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        //设置画笔
        setPaint();
        //划线
        if (mAdapter.getCount() == 2) {
            canvas.drawLine(dip(10), getTvTitleHeight(0) / 2, dip(10),
                    getHeight() - (getTvTitleHeight(getChildCount() - 1) / 2 +
                            getTvAddressHeight(getChildCount() - 1) + dip(28)), mPaint);
        } else {
            canvas.drawLine(dip(10), getTvTitleHeight(0) / 2, dip(10),
                    getHeight() - (getTvTitleHeight(getChildCount() - 1) / 2 +
                            getTvAddressHeight(getChildCount() - 1) + dip(2)), mPaint);
        }
    }

    /**
     * 获取Title的高度
     *
     * @return
     */
    private final int getTvTitleHeight(int i) {
        return getChildAt(i).findViewById(R.id.tvTitle).getHeight();
    }

    /**
     * 获取Address的高度
     *
     * @return
     */
    private final int getTvAddressHeight(int i) {
        return getChildAt(i).findViewById(R.id.tvAddress).getHeight();
    }

    /**
     * 设置画笔
     */
    private void setPaint() {
        mPaint.setColor(Color.LTGRAY);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(1);
        mPaint.setAntiAlias(true);
    }

    /**
     * 画点
     *
     * @param canvas
     */
    private void drawPoint(Canvas canvas) {
        mPaint.setColor(Color.RED);
        for (int i = 0; i < getAdapter().getCount(); i++) {
            if (i == 0) {
                //修改画笔的颜色
                mPaint.setColor(Color.RED);
                //画内圆
                canvas.drawCircle(dip(10), getHeight() / getAdapter().getCount() * i + getTvTitleHeight(i) / 2 + dip(2)
                        , 15, mPaint);
                //修改画笔的透明度
                mPaint.setAlpha(88);
                //画外圆
                canvas.drawCircle(dip(10), getHeight() / getAdapter().getCount() * i + getTvTitleHeight(i) / 2 + dip(2)
                        , 25, mPaint);
            } else if (i == getAdapter().getCount() - 1) {
                //修改画笔颜色
                mPaint.setColor(Color.parseColor("#24BFA0"));
                //画内圆
                canvas.drawCircle(dip(10), getHeight() / getAdapter().getCount() * i + getTvTitleHeight(i) / 2 + dip(2)
                        , 15, mPaint);
                //修改画笔的透明度
                mPaint.setAlpha(88);
                //画外圆
                canvas.drawCircle(dip(10), getHeight() / getAdapter().getCount() * i + getTvTitleHeight(i) / 2 + dip(2)
                        , 25, mPaint);
            } else {
                //修改画笔的透明度
                mPaint.setAlpha(255);
                //修改画笔的颜色
                mPaint.setColor(Color.LTGRAY);
                //画接点的圆
                canvas.drawCircle(dip(10), getHeight() / getAdapter().getCount() * i +
                        getChildAt(i).getHeight() / 2, 12, mPaint);
            }
        }
    }

    /**
     * 设置数据
     *
     * @param mDatas
     */
    public void setDatas(List mDatas) {
        this.mDatas = mDatas;
        //更新数据信息
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }

    /**
     * dip转成像素
     *
     * @param value
     * @return
     */
    private int dip(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                getResources().getDisplayMetrics());
    }


    /***
     * 适配器
     * requestlayout
     */
    class SpecificAdapter extends BaseAdapter {
        AbsListView.LayoutParams lp;

        public SpecificAdapter() {
            //参数的初始化
            lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip(46));
        }

        @Override
        public int getCount() {
            return mDatas == null ? 0 : mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas == null ? null : mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            NodeBean bean = (NodeBean) mDatas.get(position);
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.item_list_line_order, null);
                holder = new ViewHolder();
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                holder.tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);
                convertView.setTag(holder);
            }
            //设置参数
            convertView.setLayoutParams(lp);
            holder = (ViewHolder) convertView.getTag();
            if (position == 0 || position == mDatas.size() - 1) {
                holder.tvTitle.setVisibility(VISIBLE);
            } else {
                holder.tvTitle.setVisibility(GONE);
            }
            //设置参数
            holder.tvAddress.setText(bean.getAddress());
            holder.tvTitle.setText(bean.getName());
            //当只有两个Node的时
            if (position == mDatas.size() - 1 && getCount() == 2) {
                LayoutParams lpp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip(92));
                convertView.setLayoutParams(lpp);
            }
            return convertView;
        }

        /**
         * ViewHolder
         */
        class ViewHolder {
            private TextView tvTitle;
            private TextView tvAddress;
        }
    }
}
