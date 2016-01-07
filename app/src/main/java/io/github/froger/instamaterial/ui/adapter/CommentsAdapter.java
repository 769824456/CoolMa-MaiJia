package io.github.froger.instamaterial.ui.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.Bind;
import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.ui.utils.RoundedTransformation;

/**
 * Created by froger_mcs on 11.11.14.
 */
public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private int itemsCount = 0;
    private int lastAnimatedPosition = -1;
    private int avatarSize;

    private boolean animationsLocked = false;
    private boolean delayEnterAnimation = true;

    public CommentsAdapter(Context context) {
        this.context = context;
        avatarSize = context.getResources().getDimensionPixelSize(R.dimen.comment_avatar_size);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    String[] comm = {"手机还不错应该是正品，只不过打开包装时候手机侧面有划痕，太显眼了，客服态度很不错跳跳虎他们都很棒哦", "商品：经典行货，东西包装正品，商户很贴心，还送正背膜，以及透明套装\n" +
            "服务：小七客服很有耐性，态度也很好，多谢的他的帮助", "等一次在网上买这么贵的电子产品，卖家态度要赞一下，买前大大小小的问题，卖家都不厌其在的一一解答，手机拿到手做的第一件事就是打电话到苹果公司验证，结果是国行正品，心里的一块石头总算是落地了，用了一星期下来，还不错，可能是我比较幸运，卖家包装的很好，手机外型没有碰伤和任何瑕疵，系统也还行，和我在专卖店买的6p速度差不多，还没碰到过卡，慢，重启等问题，唯一美中不足处cpu是三星的，可能知道了是三星的CPU的心理问题，总觉的畜电就是没有我的6P(cpu台积电)来的长，不过也还好，充满电，正常用可以到第十天上午。各方面都给个大大的赞?！哇，淘宝这么久以来第一次写了这么长的评论。", "不错，配件，机器都查了，都是正品。至于说的信号不好，这是苹果的通病，其次关闭4G网络后会稳定很多。另外呢，就是屏幕边上有点黑色物质，不过用清洗剂轻轻一抹就没了。要说吐槽的地方吧！就是屏幕右下角的确按起来有点松动的感觉，跟我以前用的那两台都一样有这问题。不过对于使用没有任何影响，几乎可以忽略这瑕疵。个人感觉以上问题都是苹果的自身原因。我是觉得在这买的挺值的。\n" +
            "服务：不错，配件，机器都查了，都是正品。至于说的信号不好，这是苹果的通病，其次关闭4G网络后会稳定很多。另外呢，就是屏幕边上有点黑色物质，不过用清洗剂轻轻一抹就没了。要说吐槽的地方吧！就是屏幕右下角的确按起"};

    int[] icon = {R.drawable.ic_touxiang1, R.drawable.ic_touxiang2, R.drawable.ic_touxiang3, R.drawable.ic_touxiang4};

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        runEnterAnimation(viewHolder.itemView, position);
        CommentViewHolder holder = (CommentViewHolder) viewHolder;
        holder.tvComment.setText(comm[position % 4]);
        Picasso.with(context)
                .load(icon[position % 4])
                .centerCrop()
                .resize(avatarSize, avatarSize)
                .transform(new RoundedTransformation())
                .into(holder.ivUserAvatar);
    }

    private void runEnterAnimation(View view, int position) {
        if (animationsLocked) return;

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(100);
            view.setAlpha(0.f);
            view.animate()
                    .translationY(0).alpha(1.f)
                    .setStartDelay(delayEnterAnimation ? 20 * (position) : 0)
                    .setInterpolator(new DecelerateInterpolator(2.f))
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            animationsLocked = true;
                        }
                    })
                    .start();
        }
    }

    @Override
    public int getItemCount() {
        return itemsCount;
    }

    public void updateItems() {
        itemsCount = 10;
        notifyDataSetChanged();
    }

    public void addItem() {
        itemsCount++;
        notifyItemInserted(itemsCount - 1);
    }

    public void setAnimationsLocked(boolean animationsLocked) {
        this.animationsLocked = animationsLocked;
    }

    public void setDelayEnterAnimation(boolean delayEnterAnimation) {
        this.delayEnterAnimation = delayEnterAnimation;
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ivUserAvatar)
        ImageView ivUserAvatar;
        @Bind(R.id.tvComment)
        TextView tvComment;

        public CommentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
