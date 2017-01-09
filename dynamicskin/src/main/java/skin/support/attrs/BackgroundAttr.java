package skin.support.attrs;

import android.graphics.drawable.Drawable;
import android.view.View;

import skin.support.SkinManager;

/**
 * Created by ximsfei on 17-1-10.
 */

public class BackgroundAttr extends SkinAttr {

    @Override
    public void apply(View view) {

        if (RES_TYPE_NAME_COLOR.equals(attrValueTypeName)) {
            int color = SkinManager.getInstance().getColor(attrValueRefId);
//            if (view instanceof CardView) {
//                CardView cardView = (CardView) view;
//                cardView.setCardBackgroundColor(color);
//            } else {
                view.setBackgroundColor(color);
//            }
        } else if (RES_TYPE_NAME_DRAWABLE.equals(attrValueTypeName)) {
            Drawable bg = SkinManager.getInstance().getDrawable(attrValueRefId);
            view.setBackgroundDrawable(bg);
        }
    }
}
