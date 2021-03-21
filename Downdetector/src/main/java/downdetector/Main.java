package downdetector;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.concurrent.Task;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {
            JDA jda = JDABuilder.createDefault(Config.TOKEN, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES).build();
            jda.awaitReady();
            Guild guild = jda.getGuildById(Config.SERVERID);
            while (true) {
                System.out.println("Members:");
                Task<List<Member>> members = guild.loadMembers().onSuccess(members1 -> {
                    members1.forEach(member -> {
                        System.out.println(member.getUser().getAsTag() + ";" + member.getId());
                    });
                });
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}