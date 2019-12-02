package com.example.restaurants;

public class Restaurant {
    private String restaurantName;
    private String restaurantURL;

    private void setRestaurantInfo(Integer cuisineType){
        switch (cuisineType){
            case 0: //American
                restaurantName="Boulder Cork";
                restaurantURL="https://bouldercork.com/";
                break;
            case 1: //Japanese
                restaurantName="Japango";
                restaurantURL="https://boulderjapango.com/";
                break;
            case 2: //Persian
                restaurantName="The Boulder Dushanbe Teahouse";
                restaurantURL="https://www.boulderteahouse.com/";
                break;
            case 3: //Chinese
                restaurantName="Five Spice";
                restaurantURL="https://www.fivespiceboulder.com/?utm_source=tripadvisor&utm_medium=referral";
                break;
            case 4: //European
                restaurantName="Brasserie Ten Ten";
                restaurantURL="https://www.brasserietenten.com/?utm_source=tripadvisor&utm_medium=referral";
                break;
            default:
                restaurantName="none";
                restaurantURL="https://www.google.com/search?q=boulder+restaurant&ie=utf-8&oe=utf-8";
        }
    }


    public void setRestaurantName(Integer cuisineType){

        setRestaurantInfo(cuisineType);
    }

    public void setRestaurantURL(Integer cuisineType){

        setRestaurantInfo(cuisineType);
    }

    public String getRestaurantName(){
        return restaurantName;
    }

    public String getRestaurantURL(){
        return restaurantURL;
    }



}
