package com.example.project;

public class Rating {
    private long num_rates;
    private long rating;
    private long rating_total;

    public Rating(){
    }
    public Rating(long num, long rating, long rating_total){
        this.num_rates=num;
        this.rating=rating;
        this.rating_total=rating_total;
    }

    public void addRate(int rating){
        this.num_rates++;
        rating_total+=rating;
        this.rating= (rating_total)/num_rates;
    }

    public long getRating_total() {
        return rating_total;
    }

    public long getNum_rates() {
        return num_rates;
    }

    public long getRating() {
        return rating;
    }
}
