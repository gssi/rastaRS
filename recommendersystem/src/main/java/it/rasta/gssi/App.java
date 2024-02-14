package it.rasta.gssi;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.CityBlockSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;


/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            DataModel model = new FileDataModel(new File(("data/data.csv")));
            
           //Creating UserSimilarity object.
         UserSimilarity usersimilarity = new PearsonCorrelationSimilarity(model);
      
         //Creating UserNeighbourHHood object.
         UserNeighborhood userneighborhood = new ThresholdUserNeighborhood(1.0, usersimilarity, model);
      
         //Create UserRecomender
         UserBasedRecommender recommender = new GenericUserBasedRecommender(model, userneighborhood, usersimilarity);
        
         List<RecommendedItem> recommendations = recommender.recommend(2, 3);
			
         for (RecommendedItem recommendation : recommendations) {
            System.out.println(recommendation);
         }

        } catch (Exception e) {
            System.out.println("Exception occured !");
        }
        

    }
}
