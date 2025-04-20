import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static Document getPage() throws IOException {
        String url = "http://gozm.myarena.ru/serverinfo/index.html";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }


    public static void main(String[] args) throws Exception {
        Document page = getPage();
        // css quary language
        Elements players = page.select("tr[class=context-menu-one]");
        Elements playerStats = players.select("td"); //дает только td
        Elements playerNicknames = playerStats.select("td:nth-child(1)");
        Elements playerScores = playerStats.select("td:nth-child(2)");
        Elements playerSessions = playerStats.select("td:nth-child(3)");
        int playersCount = playerNicknames.size();

        Elements info = page.select("table:not(table[id=players])");
        Element mapName = info.select("td:contains(Карта)").next().first();


        Player[] playersArr = new Player[playersCount];
        for (int i = 0; i < playersCount; i++) {
            playersArr[i] = new Player(playerNicknames.get(i).text(), Integer.parseInt(playerScores.get(i).text()), playerSessions.get(i).text());
        }
        System.out.println("Карта: " + mapName.text());
        System.out.println("Количество игроков: " + playersCount + "/30");
        for (Player plr : playersArr) {
            plr.getInfo();
        }
    }
}
