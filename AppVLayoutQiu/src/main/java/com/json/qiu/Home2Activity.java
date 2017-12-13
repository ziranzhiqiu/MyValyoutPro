package com.json.qiu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JsonQiu on 2017/12/12.
 */
public class Home2Activity extends AppCompatActivity {

    public static final String TAG = "Home2Activity";

    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private static final boolean BANNER_LAYOUT = true;

    private static final boolean CROSS_LAYOUT = true;

    private static final boolean LINEAR_LAYOUT = true;

    private static final boolean SINGLE_LAYOUT = true;

    private static final boolean GRID_LAYOUT = true;

    private static final boolean STICKY_LAYOUT = true;

    private static final boolean TITLE_LAYOUT = true;

    private static final boolean FOOTER_LAYOUT = true;

    private SubAdapter adapter_search;
    private SubAdapter adapter_linear;
    private SubAdapter adapter_grid_head;
    private SubAdapter adapter_grid_middle;
    private SubAdapter adapter_singel;
    private SubAdapter adapter_singel_title1;
    private SubAdapter adapter_singel_title2;
    private SubAdapter adapter_singel_title3;
    private SubAdapter adapter_singel_title4;
    private SubAdapter adapter_skill;
    private SubAdapter adapter_footer;

    private List<String> list = new ArrayList<>();
    private boolean hasmore = true;
    private int page = 1;
    private BannerAdapter bAdapter;

    private Handler handler = new Handler();
    private Home2Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2_main);
        mContext = this;
        Log.e(TAG, "-----2222222222222222222-----");
        Toast.makeText(this, "Home2Activity", Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView) this.findViewById(R.id.recycler_2_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, false);
        recyclerView.setAdapter(delegateAdapter);


        initOnScrollListener();


        final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        if (BANNER_LAYOUT) {
        }


        // 粘性头部 搜索
        if (STICKY_LAYOUT) {
            StickyLayoutHelper layoutHelper = new StickyLayoutHelper();
            layoutHelper.setBgColor(Color.RED);
//            为啥设置无效呢？   setMargin——setPadding——setBgColor ？？JsonQiu？？谁知道告诉我，谢谢！ QQ  1143986647
//            layoutHelper.setBgColor(mContext.getResources().getColor(R.color.colorGrayBg));
            adapter_search = new SubAdapter(this, layoutHelper, 1, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(50))) {
                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new MainViewHolder(LayoutInflater.from(Home2Activity.this).inflate(R.layout.item_search, parent, false));
                }

                @Override
                public void onBindViewHolder(MainViewHolder holder, final int position) {
                    super.onBindViewHolder(holder, position);
                    holder.itemView.findViewById(R.id.search_main).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, " 点击   搜索   的位置 position = " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };
            adapters.add(adapter_search);
        }


        //网格布局 分类
        if (GRID_LAYOUT) {
            GridLayoutHelper layoutHelper = new GridLayoutHelper(4);
            adapter_grid_head = new SubAdapter(this, layoutHelper, 8, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(100))) {
                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new MainViewHolder(LayoutInflater.from(Home2Activity.this).inflate(R.layout.item, parent, false));
                }

                @Override
                public void onBindViewHolder(final MainViewHolder holder, final int position) {
                    super.onBindViewHolder(holder, position);
                    ImageView imageView = holder.itemView.findViewById(R.id.img);
                    TextView title = holder.itemView.findViewById(R.id.title);
                    if (position == 0) {
                        imageView.setImageResource(R.drawable.index_03);
                        title.setText(R.string.one);
                    } else if (position == 1) {
                        imageView.setImageResource(R.drawable.index_05);
                        title.setText(R.string.two);
                    } else if (position == 2) {
                        imageView.setImageResource(R.drawable.index_07);
                        title.setText(R.string.three);
                    } else if (position == 3) {
                        imageView.setImageResource(R.drawable.index_11);
                        title.setText(R.string.four);
                    } else if (position == 4) {
                        imageView.setImageResource(R.drawable.index_15);
                        title.setText(R.string.five);
                    } else if (position == 5) {
                        imageView.setImageResource(R.drawable.index_16);
                        title.setText(R.string.six);
                    } else if (position == 6) {
                        imageView.setImageResource(R.drawable.index_17);
                        title.setText(R.string.seven);
                    } else if (position == 7) {
                        imageView.setImageResource(R.drawable.index_18);
                        title.setText(R.string.eight);
                    }
                    holder.itemView.findViewById(R.id.main_grid).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, " 点击   -" + ((TextView) (holder.itemView.findViewById(R.id.title))).getText() + "-   的位置 position = " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };
            adapters.add(adapter_grid_head);
        }


        //标题栏 名厨直播
        if (TITLE_LAYOUT) {
            adapter_singel_title1 = new TitleAdapter(Home2Activity.this, getTitleHelper()) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                }

                @Override
                protected String getText() {
                    return getString(R.string.title1);
                }

                @Override
                protected int[] getDrawables() {
                    return new int[]{R.drawable.index_zb, R.drawable.arrow};
                }

            };
            adapters.add(adapter_singel_title1);
        }
        //单行布局 名厨直播
        if (SINGLE_LAYOUT) {
            SingleLayoutHelper layoutHelper = new SingleLayoutHelper();
            adapter_singel = new SubAdapter(this, layoutHelper, 1) {
                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new MainViewHolder(LayoutInflater.from(Home2Activity.this).inflate(R.layout.item_zb, parent, false));
                }

                @Override
                public void onBindViewHolder(MainViewHolder holder, final int position) {
                    super.onBindViewHolder(holder, position);
                    holder.itemView.findViewById(R.id.headimg).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, " 点击   -名厨直播-图片   的位置 position = " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    holder.itemView.findViewById(R.id.title).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, " 点击   -名厨直播-标题   的位置 position = " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    holder.itemView.findViewById(R.id.time).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, " 点击   -名厨直播-时间   的位置 position = " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    holder.itemView.findViewById(R.id.author).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, " 点击   -名厨直播-作者   的位置 position = " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };
            adapters.add(adapter_singel);
        }
        //单行布局 广告
        if (SINGLE_LAYOUT) {
            SingleLayoutHelper layoutHelper = new SingleLayoutHelper();
            layoutHelper.setMarginTop(20);
            adapter_singel = new SubAdapter(this, layoutHelper, 1) {
                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new MainViewHolder(LayoutInflater.from(Home2Activity.this).inflate(R.layout.item_img, parent, false));
                }

                @Override
                public void onBindViewHolder(MainViewHolder holder, final int position) {
                    super.onBindViewHolder(holder, position);
                    holder.itemView.findViewById(R.id.ad_pic).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, " 点击   -广告-   的位置 position = " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };
            adapters.add(adapter_singel);
        }
        //标题栏 发现好厨
        if (TITLE_LAYOUT) {
            adapter_singel_title2 = new TitleAdapter(Home2Activity.this, getTitleHelper()) {
                @Override
                protected String getText() {
                    return getString(R.string.title2);
                }

                @Override
                protected int[] getDrawables() {
                    return new int[]{R.drawable.location, R.drawable.arrow};
                }
            };
            adapters.add(adapter_singel_title2);
        }

        //网格布局 发现好厨
        if (GRID_LAYOUT) {
            GridLayoutHelper layoutHelper = new GridLayoutHelper(4);
            adapter_grid_middle = new SubAdapter(this, layoutHelper, 4) {
                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new MainViewHolder(LayoutInflater.from(Home2Activity.this).inflate(R.layout.item, parent, false));
                }

                @Override
                public void onBindViewHolder(MainViewHolder holder, final int position) {
                    super.onBindViewHolder(holder, position);
                    ImageView imageView = holder.itemView.findViewById(R.id.img);
                    if (position == 0) {
                        imageView.setImageResource(R.drawable.index_round1);
                    } else if (position == 1) {
                        imageView.setImageResource(R.drawable.index_round2);
                    } else if (position == 2) {
                        imageView.setImageResource(R.drawable.index_round3);
                    } else if (position == 3) {
                        imageView.setImageResource(R.drawable.index_round4);
                    }
                    holder.itemView.findViewById(R.id.main_grid).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, "发现好厨    点击的位置 position = " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };
            adapters.add(adapter_grid_middle);
        }


        //标题栏 菜品技术
        if (TITLE_LAYOUT) {
            adapter_singel_title3 = new TitleAdapter(Home2Activity.this, getTitleHelper()) {
                @Override
                protected String getText() {
                    return getString(R.string.title3);
                }

                @Override
                protected int[] getDrawables() {
                    return new int[]{R.drawable.index_59, R.drawable.arrow};
                }
            };
            adapters.add(adapter_singel_title3);
        }
        //banner布局 菜品技术
        if (CROSS_LAYOUT) {
            SingleLayoutHelper layoutHelper = new SingleLayoutHelper();
            adapter_skill = new SubAdapter(this, layoutHelper, 1) {
                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new MainViewHolder(LayoutInflater.from(Home2Activity.this).inflate(R.layout.item_skill, parent, false));
                }

                @Override
                public void onBindViewHolder(MainViewHolder holder, final int position) {
                    super.onBindViewHolder(holder, position);
                    RecyclerView recyclerView = holder.itemView.findViewById(R.id.banner);
                    final TextView tvTitle = holder.itemView.findViewById(R.id.title);
                    final TextView tvAuthor = holder.itemView.findViewById(R.id.author);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Home2Activity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    bAdapter = new BannerAdapter(Home2Activity.this);
                    recyclerView.setAdapter(bAdapter);

                    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                            super.onScrollStateChanged(recyclerView, newState);
                        }

                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            int position = (int) recyclerView.findChildViewUnder(200, 200).getTag();
                            tvTitle.setText(getResources().getStringArray(R.array.chiyidun)[position]);
                        }
                    });

                    holder.itemView.findViewById(R.id.title).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, "点击   -Banner标题-  " + tvTitle.getText() + "   的位置 position = " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    holder.itemView.findViewById(R.id.author).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, "点击   -Banner作者-  " + tvAuthor.getText() + "   的位置 position = " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };
            adapters.add(adapter_skill);
        }


        //标题栏 特色食材
        if (TITLE_LAYOUT) {
            adapter_singel_title4 = new TitleAdapter(Home2Activity.this, getTitleHelper()) {
                @Override
                protected String getText() {
                    return getString(R.string.title4);
                }

                @Override
                protected int[] getDrawables() {
                    return new int[]{R.drawable.index_left, R.drawable.index_right};
                }
            };
            adapters.add(adapter_singel_title4);
        }

        //单行列表布局  特色食材
        if (LINEAR_LAYOUT) {
            for (int i = 0; i < 10; i++) {
                list.add("abc");
            }
            LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
            adapter_linear = new SubAdapter(this, linearLayoutHelper, list.size()) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, final int position) {
                    super.onBindViewHolder(holder, position);

                    ((TextView) (holder.itemView.findViewById(R.id.oldprice))).setText("12.0" + position);
                    ((TextView) (holder.itemView.findViewById(R.id.nowprice))).setText("10.0" + position);
                    holder.itemView.findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, "图片+++LINEAR_LAYOUT    点击的位置 position = " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    holder.itemView.findViewById(R.id.name).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, "文字+++LINEAR_LAYOUT    点击的位置 position = " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    holder.itemView.findViewById(R.id.add_to).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, "加入+++LINEAR_LAYOUT    点击的位置 position = " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new MainViewHolder(LayoutInflater.from(Home2Activity.this).inflate(R.layout.item2, parent, false));
                }

                @Override
                public int getItemCount() {
                    return list.size();
                }
            };
            adapters.add(adapter_linear);
        }


        //加载更多布局
        if (FOOTER_LAYOUT) {
            SingleLayoutHelper layoutHelper = new SingleLayoutHelper();
            adapter_footer = new SubAdapter(this, layoutHelper, 1, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)) {
                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new MainViewHolder(LayoutInflater.from(Home2Activity.this).inflate(R.layout.item_footer, parent, false));
                }

                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    TextView textView = holder.itemView.findViewById(R.id.textview);
                    ProgressBar progressBar = holder.itemView.findViewById(R.id.progressbar);
                    if (hasmore) {
                        textView.setText(R.string.loading);
                        progressBar.setVisibility(View.VISIBLE);
                    } else {
                        textView.setText(R.string.loadfinish);
                        progressBar.setVisibility(View.GONE);
                    }
                }
            };
            adapters.add(adapter_footer);
        }
        delegateAdapter.setAdapters(adapters);
    }


    private SingleLayoutHelper getTitleHelper() {
        SingleLayoutHelper helper = new SingleLayoutHelper();
        helper.setMarginTop(20);
        return helper;
    }


    static class SubAdapter extends DelegateAdapter.Adapter<MainViewHolder> {

        Context mContext;

        private LayoutHelper mLayoutHelper;


        private VirtualLayoutManager.LayoutParams mLayoutParams;
        private int mCount = 0;


        public SubAdapter(Context context, LayoutHelper layoutHelper, int count) {
            this(context, layoutHelper, count, null);
        }

        public SubAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
            this.mContext = context;
            this.mLayoutHelper = layoutHelper;
            this.mCount = count;
            this.mLayoutParams = layoutParams;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return mLayoutHelper;
        }

        @Override
        protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            if (mLayoutParams != null) {
                holder.itemView.setLayoutParams(
                        new VirtualLayoutManager.LayoutParams(mLayoutParams));
            }
        }

        @Override
        public int getItemCount() {
            return mCount;
        }
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {

        public MainViewHolder(View itemView) {
            super(itemView);
        }

    }


    abstract class TitleAdapter extends SubAdapter {

        public TitleAdapter(Context context, LayoutHelper layoutHelper) {
            super(context, layoutHelper, 1);
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_title, parent, false));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, final int position) {
            holder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(45)));
            TextView textView = holder.itemView.findViewById(R.id.title);
            textView.setText(getText());
            Drawable drawableLeft = getResources().getDrawable(getDrawables()[0]);
            Drawable drawableRight = getResources().getDrawable(getDrawables()[1]);
            drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
            drawableRight.setBounds(0, 0, drawableRight.getMinimumWidth(), drawableRight.getMinimumHeight());
            textView.setCompoundDrawables(drawableLeft, null, drawableRight, null);

            holder.itemView.findViewById(R.id.title_main).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(mContext, getText() + "    点击的位置 position = " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        protected abstract String getText();


        protected abstract int[] getDrawables();
    }

    class BannerAdapter extends RecyclerView.Adapter<BannerViewHolder> {

        Context context;

        public BannerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public BannerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BannerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_skll_inside, parent, false));
        }

        @Override
        public void onBindViewHolder(BannerViewHolder holder, final int position) {
            holder.itemView.setTag(position);
            holder.itemView.findViewById(R.id.headimg).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "BannerAdapter 点击的位置 position = " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return getResources().getStringArray(R.array.chiyidun).length;
        }

    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView headimg;

        public BannerViewHolder(View itemView) {
            super(itemView);
            headimg = itemView.findViewById(R.id.headimg);

        }

    }

    private int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void initOnScrollListener() {
        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {

            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            }

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (hasmore) {
                    VirtualLayoutManager lm = (VirtualLayoutManager) recyclerView.getLayoutManager();
                    int last = 0, total = 0;
                    last = lm.findLastVisibleItemPosition();
                    total = recyclerView.getAdapter().getItemCount();
                    if (last > 0 && last >= total - 1) {
                        hasmore = false;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (page < 2) {
                                    for (int i = 0; i < 10; i++) {
                                        list.add("123");
                                    }
                                    adapter_linear.notifyDataSetChanged();
                                    page++;
                                    hasmore = true;
                                } else {
                                    hasmore = false;
                                    adapter_footer.notifyDataSetChanged();
                                }
                            }
                        }, 2000);

                    }
                }
            }
        };
        recyclerView.addOnScrollListener(onScrollListener);
    }
}
