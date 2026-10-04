package interfaces;

import models.Review;

import java.util.List;

public interface Reviewable {
	void addReview(Review review);
	List<Review> getReviews();

	default double getAverageRating(){
		List<Review> reviews = getReviews();

		if(reviews == null || reviews.isEmpty()){
			return 0.0;
		}

		int sum = 0;
		 for (Review review : reviews ){
			 sum += review.getRating();
		 }

		 return (double) sum / reviews.size();
	}
}
