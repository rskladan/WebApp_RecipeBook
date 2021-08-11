package pl.coderslab.dao;

import pl.coderslab.model.Admins;
import pl.coderslab.model.Plan;
import pl.coderslab.model.PlanDetails;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanDetailsDao {


    private static final String LAST_ADDED_PLAN_DETAILS_QUERY = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description\n" +
            "FROM `recipe_plan`\n" +
            "JOIN day_name on day_name.id=day_name_id\n" +
            "JOIN recipe on recipe.id=recipe_id WHERE\n" +
            "recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?) \n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";

    private static final String FIND_PLAN_QUERY = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description\n" +
            "FROM `recipe_plan`\n" +
            "JOIN day_name on day_name.id=day_name_id\n" +
            "JOIN recipe on recipe.id=recipe_id\n" +
            "WHERE recipe_plan.plan_id = ? \n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";



    public List<PlanDetails> findLastAddedPlanDetails(int id) {
        List<PlanDetails> planDetailsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()){
             PreparedStatement statement = connection.prepareStatement(LAST_ADDED_PLAN_DETAILS_QUERY);
             statement.setInt(1,id);
             ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PlanDetails planDetailsToAdd = new PlanDetails();
                planDetailsToAdd.setDayName(resultSet.getString("day_name"));
                planDetailsToAdd.setMealName(resultSet.getString("meal_name"));
                planDetailsToAdd.setRecipeName(resultSet.getString("recipe_name"));
                planDetailsToAdd.setRecipeDescription(resultSet.getString("recipe_description"));
                planDetailsList.add(planDetailsToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planDetailsList;
    }

    public List<PlanDetails> findAllDetailsFromPlan (int planId) {
        List<PlanDetails> planDetailsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement(FIND_PLAN_QUERY);
            statement.setInt(1,planId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PlanDetails planDetailsToAdd = new PlanDetails();
                planDetailsToAdd.setDayName(resultSet.getString("day_name"));
                planDetailsToAdd.setMealName(resultSet.getString("meal_name"));
                planDetailsToAdd.setRecipeName(resultSet.getString("recipe_name"));
                planDetailsToAdd.setRecipeDescription(resultSet.getString("recipe_description"));
                planDetailsList.add(planDetailsToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planDetailsList;
    }

}


