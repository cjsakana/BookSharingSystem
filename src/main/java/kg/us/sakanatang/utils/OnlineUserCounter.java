package kg.us.sakanatang.utils;
import java.util.HashSet;
import java.util.Set;

public class OnlineUserCounter {
    private static final Set<String> onlineUsers = new HashSet<>();

    public static synchronized void addUser(String sessionId) {
        onlineUsers.add(sessionId);
    }

    public static synchronized void removeUser(String sessionId) {
        onlineUsers.remove(sessionId);
    }

    public static synchronized int getOnlineUserCount() {
        return onlineUsers.size();
    }

    public static boolean containsUser(String sessionId) {
        return onlineUsers.contains(sessionId);
    }
}