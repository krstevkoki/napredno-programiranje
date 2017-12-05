package mk.ukim.finki.lab7;

import java.util.TreeSet;

class ChatRoom {
    private String roomName;
    private TreeSet<String> users;

    public ChatRoom(String roomName) {
        this.roomName = roomName;
        this.users = new TreeSet<>();
    }

    public String getRoomName() {
        return roomName;
    }

    public void addUser(String username) {
        users.add(username);
    }

    public void removeUser(String username) {
        users.remove(username);
    }

    public boolean hasUser(String username) {
        return users.contains(username);
    }

    public int numUsers() {
        return users.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s\n", getRoomName()));
        if (users.isEmpty())
            sb.append("EMPTY\n");
        else
            users.forEach(user -> sb.append(String.format("%s\n", user)));
        return sb.toString();
    }
}
