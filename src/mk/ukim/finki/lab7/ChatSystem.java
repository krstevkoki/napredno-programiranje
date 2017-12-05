package mk.ukim.finki.lab7;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;

class ChatSystem {
    private HashSet<String> users;
    private TreeMap<String, ChatRoom> chatRooms;

    public ChatSystem() {
        this.chatRooms = new TreeMap<>();
        this.users = new HashSet<>();
    }

    public void addRoom(String roomName) {
        chatRooms.put(roomName, new ChatRoom(roomName));
    }

    public void removeRoom(String roomName) {
        chatRooms.remove(roomName);
    }

    public ChatRoom getRoom(String roomName) throws NoSuchRoomException {
        if (chatRooms.containsKey(roomName))
            return chatRooms.get(roomName);
        throw new NoSuchRoomException(roomName);
    }

    private ChatRoom findMinUsersRoom() {
        if (chatRooms.isEmpty())
            return null;
        ChatRoom min = chatRooms.get(chatRooms.firstKey());
        for (String roomName : chatRooms.keySet()) {
            ChatRoom currentRoom = chatRooms.get(roomName);
            if (currentRoom.numUsers() < min.numUsers())
                min = currentRoom;
        }
        return min;
    }

    public void register(String userName) throws NoSuchRoomException, NoSuchUserException {
        users.add(userName);
        ChatRoom minUsersRoom = findMinUsersRoom();
        if (minUsersRoom == null)
            return;
        joinRoom(userName, minUsersRoom.getRoomName());
    }

    public void registerAndJoin(String userName, String roomName) throws NoSuchRoomException, NoSuchUserException {
        users.add(userName);
        joinRoom(userName, roomName);
    }

    private void checkIfExists(String userName, String roomName) throws NoSuchRoomException, NoSuchUserException {
        if (!chatRooms.containsKey(roomName))
            throw new NoSuchRoomException(roomName);
        if (!users.contains(userName))
            throw new NoSuchUserException(userName);
    }

    public void joinRoom(String userName, String roomName) throws NoSuchRoomException, NoSuchUserException {
        checkIfExists(userName, roomName);
        chatRooms.computeIfPresent(roomName, (rName, room) -> {
            room.addUser(userName);
            return room;
        });
    }

    public void leaveRoom(String userName, String roomName) throws NoSuchUserException, NoSuchRoomException {
        checkIfExists(userName, roomName);
        chatRooms.computeIfPresent(roomName, (rName, room) -> {
            room.removeUser(userName);
            return room;
        });
    }

    public void followFriend(String username, String friendUsername) throws NoSuchUserException {
        if (!users.contains(username))
            throw new NoSuchUserException(username);
        LinkedList<ChatRoom> rooms = new LinkedList<>(chatRooms.values());
        for (ChatRoom room : rooms)
            if (room.hasUser(friendUsername))
                room.addUser(username);
    }
}
