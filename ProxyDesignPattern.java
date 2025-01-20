// Problem: Video Streaming Service

// You are tasked with implementing a video streaming service that uses a proxy to manage access to videos. The proxy will handle the following features:

//     Access Control:
//         Free users can only access standard-definition (SD) videos.
//         Premium users can access both SD and high-definition (HD) videos.

//     Caching:
//         If a video has already been accessed by a user, the proxy should fetch it from the cache instead of streaming it again from the real service.

//     Logging:
//         Every video access attempt should be logged, including whether it was retrieved from the cache or streamed anew.

import java.util.ArrayList;
import java.util.List;

enum UserType {
  FREE_USER,
  PREMIUM_USER
}

interface StreamingService {
  public abstract String getSDVideo(UserType userType);
  public abstract String getHDVideo(UserType userType);
  public abstract List<UserType> getAccessUserList();
}

class StreamingServiceImpl implements StreamingService {
  private String sdVideo = "this is the s3 link of the SD video";
  private String hdVideo = "this is the s3 link of the HD video";
  public String getSDVideo(UserType userType) { return sdVideo; }
  public String getHDVideo(UserType userType) { return hdVideo; }
  public List<UserType> getAccessUserList() { return null; }
}

class StreamingServiceProxy implements StreamingService{
  private String cacheSDVideo = null;
  private String cacheHDVideo = null;
  public StreamingService streamingServiceImpl = null;
  List<UserType> accessUser = new ArrayList<>();

  private void recordAccessUser(UserType userType) {
    accessUser.add(userType);
  }
  
  private void initializeStreamingServiceImpl() { 
    if (this.streamingServiceImpl == null)  this.streamingServiceImpl = new StreamingServiceImpl(); 
  }

  public List<UserType> getAccessUserList() {
    return accessUser;
  }

  public String getSDVideo(UserType userType) {
    initializeStreamingServiceImpl();
    recordAccessUser(userType);
    if (cacheSDVideo == null) {
      cacheSDVideo = streamingServiceImpl.getSDVideo(userType);
    }
    return cacheSDVideo;
  }

  public String getHDVideo(UserType userType) {
    initializeStreamingServiceImpl();
    recordAccessUser(userType);
    if (userType == userType.FREE_USER) {
      System.out.println("Unable to see the HD Quality video as you are not a premium user");
      return null;
    }
    if (cacheHDVideo == null) {
      cacheHDVideo = streamingServiceImpl.getHDVideo(userType);
    }
    return cacheHDVideo;
  }
}

public class ProxyDesignPattern {
  public static void main(String []args) {
    StreamingService ssProxy = new StreamingServiceProxy();
    String userVideo;
    userVideo = ssProxy.getSDVideo(UserType.FREE_USER);
    System.out.println(userVideo);
    userVideo = ssProxy.getSDVideo(UserType.PREMIUM_USER);
    System.out.println(userVideo);
    userVideo = ssProxy.getHDVideo(UserType.FREE_USER);
    System.out.println(userVideo);
    userVideo = ssProxy.getHDVideo(UserType.PREMIUM_USER);
    System.out.println(userVideo);
    List<UserType> accessUserList = ssProxy.getAccessUserList();
    System.out.println("Users who accessed the data: ");
    for (UserType accessUser: accessUserList) {
      System.out.println(accessUser);
    }
  }
}