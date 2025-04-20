public class Player {
    String nickname;
    int score;
    String session;

    Player(String nickname, int score, String session) {
        this.nickname = nickname;
        this.score = score;
        this.session = session;
    }

    void getInfo() {
        System.out.println(nickname + " " + score + " " + session);
    }
}
