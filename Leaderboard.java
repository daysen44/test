package com.example.cs2340a.dungenCrawler.model;


public class Leaderboard {
    //singleton class with getters and setters
    private static String[] names = new String[6];
    private static int[] scores = new int[6];
    private static String[] times = new String[6];

    private static Leaderboard instance;

    private Leaderboard(String[] names, int[] scores, String[] times) {
        if (names == null) {
            this.names = new String[5];
            for (int i = 0; i < 5; i++) {
                names[i] = "XXXX";
            }
        } else {
            this.names = names;
        }
        //if scores array is null, make every score 0 since int can't be null
        if (scores == null) {
            this.scores = new int[5];
            for (int i = 0; i < 5; i++) {
                scores[i] = 0;
            }
        } else {
            this.scores = scores;
        }

        if (times == null) {
            this.times = new String[5];
            for (int i = 0; i < 5; i++) {
                times[i] = "XXXX";
            }
        } else {
            this.times = times;
        }
    }

    //one instance of leaderboard
    public static synchronized Leaderboard getInstance() {
        if (instance == null) {
            instance = new Leaderboard(names, scores, times);
        }
        return instance;
    }

    //getters and setters
    public String[] getNames() {
        return names;
    }

    public int[] getScores() {
        return scores;
    }
    public String[] getTimes() {
        return times;
    }

    public void setScore(int score, int index) {
        scores[index] = score;
    }

    public void setName(String name, int index) {
        names[index] = name;
    }

    public void addScores(int score, String name, String time) {
        for (int i = 0; i < 5; i++) {
            if (scores[i] == 0) {
                scores[i] = score;
                names[i] = name;
                times[i] = time;
                break;
            } else if (scores[i] > score) {
                continue;
            } else if (scores[i] < score) {
                if (scores[i + 1] == 0) {
                    scores[i + 1] = scores[i];
                    names[i + 1] = names[i];
                    times[i + 1] = times[i];
                    scores[i] = score;
                    names[i] = name;
                    times[i] = time;
                    break;
                } else {
                    int count = i;
                    scores[5] = score;
                    names[5] = name;
                    times[5] = time;
                    bubbleSort(scores);
                    break;
                }
            }
        }
    }

    private void bubbleSort(int[] arr) {
        int n = arr.length;
        int temp = 0;
        String tempName;
        String tempTime;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] < arr[j]) {
                    temp = arr[j - 1];
                    tempName = names[j - 1];
                    tempTime = times[j - 1];
                    arr[j - 1] = arr[j];
                    names[j - 1] = names[j];
                    times[j - 1] = times[j];
                    arr[j] = temp;
                    names[j] = tempName;
                    times[j] = tempTime;
                }
            }
        }
    }
}