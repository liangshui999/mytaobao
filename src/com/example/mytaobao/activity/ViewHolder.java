package com.example.mytaobao.activity;

import android.widget.CheckBox;
import android.widget.TextView;

public class ViewHolder {
	
		private android.widget.ImageView ImageView;
		private TextView productName;
		private TextView productPrice;
		private CheckBox checkBox;

		public android.widget.ImageView getImageView() {
			return ImageView;
		}

		public void setImageView(android.widget.ImageView imageView) {
			ImageView = imageView;
		}

		public TextView getProductName() {
			return productName;
		}

		public void setProductName(TextView productName) {
			this.productName = productName;
		}

		public TextView getProductPrice() {
			return productPrice;
		}

		public void setProductPrice(TextView productPrice) {
			this.productPrice = productPrice;
		}

		public CheckBox getCheckBox() {
			return checkBox;
		}

		public void setCheckBox(CheckBox checkBox) {
			this.checkBox = checkBox;
		}
		



}
