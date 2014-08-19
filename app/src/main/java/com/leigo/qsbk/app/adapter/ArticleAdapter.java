package com.leigo.qsbk.app.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leigo.qsbk.app.Constants;
import com.leigo.qsbk.app.R;
import com.leigo.qsbk.app.model.Article;
import com.leigo.qsbk.app.model.User;
import com.leigo.qsbk.app.widget.CircleImageView;
import com.leigo.qsbk.app.widget.HighlightableImageButton;

/**
 * Created by Administrator on 2014/8/19.
 */
public class ArticleAdapter extends DefaultAdapter<Article> {


    public ArticleAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_article_item, null);
            viewHolder = new ViewHolder();
            viewHolder.userInfo = (RelativeLayout) convertView.findViewById(R.id.userInfo);
            viewHolder.userIcon = (CircleImageView) convertView.findViewById(R.id.userIcon);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.userName);
            viewHolder.content = (TextView) convertView.findViewById(R.id.content);
            viewHolder.imageLayout = (RelativeLayout) convertView.findViewById(R.id.imageLayout);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.progress = (ProgressBar) convertView.findViewById(R.id.progress);
            viewHolder.points_and_comments_count = (TextView) convertView.findViewById(R.id.points_and_comments_count);
            viewHolder.location = (TextView) convertView.findViewById(R.id.location);
            viewHolder.support = (HighlightableImageButton) convertView.findViewById(R.id.support);
            viewHolder.unsupport = (HighlightableImageButton) convertView.findViewById(R.id.unsupport);
            viewHolder.comment = (ImageButton) convertView.findViewById(R.id.comment);
            viewHolder.collection_icon = (ImageButton) convertView.findViewById(R.id.collection_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Article article = mList.get(position);
        if (article.user == null) {
            viewHolder.userInfo.setVisibility(View.GONE);
        } else {
            User user = article.user;
            viewHolder.userInfo.setVisibility(View.VISIBLE);
            viewHolder.userName.setText(user.login);
            if (!TextUtils.isEmpty(user.id) && !TextUtils.isEmpty(user.icon)) {
                String avatarUrl = String.format(Constants.ARATAR_URL, Integer.parseInt(user.id) / 10000, user.id, "medium", user.icon);
                mImageLoader.displayImage(avatarUrl, viewHolder.userIcon);
            }

        }
        viewHolder.content.setText(article.content);
        viewHolder.imageLayout.setVisibility(View.GONE);
        viewHolder.points_and_comments_count.setText(mContext.getString(R.string.points_and_count, article.votes.up + article.votes.down, article.comments_count));
        return convertView;
    }

    private static class ViewHolder {
        RelativeLayout userInfo;
        CircleImageView userIcon;
        TextView userName;
        TextView content;
        RelativeLayout imageLayout;
        ImageView image;
        ProgressBar progress;
        TextView points_and_comments_count;
        TextView location;
        HighlightableImageButton support;
        HighlightableImageButton unsupport;
        ImageButton comment;
        ImageButton collection_icon;
    }
}
