// Question: Media Player System

// You are tasked with designing a Media Player System that can play different audio formats. Your application uses a standard MediaPlayer interface that supports playing MP3 files. However, you need to integrate it with a third-party library that supports playing additional formats like MP4 and VLC. Use the Adapter Design Pattern to bridge the gap.

enum MusicType {
  MP3,
  MP4,
  VLC
}

interface MediaPlayer {
  void play(MusicType musicType, String filename) throws Exception;
}

class BasicPlayer implements MediaPlayer {
  public void play(MusicType musicType, String filename) throws Exception {
    if (musicType == MusicType.MP3) {
      System.out.println("playing MP3 song");
    }
    else {
      new AdvancedMediaPlayerFactory().getMediaPlayer(musicType).play(filename);
    }
  }
}

class AdvancedMediaPlayerFactory {
  AdvancedMediaPlayer getMediaPlayer(MusicType musicType) throws Exception {
    return switch(musicType) {
      case MP4 -> new MP4MediaPlayer();
      case VLC -> new VLCMediaPlayer();
      default -> throw new Exception("Unable to find the requested media player");
    };
  }
}

interface AdvancedMediaPlayer {
  public void play(String filename);
}

class MP4MediaPlayer implements AdvancedMediaPlayer {
  public void play(String filename) {
    System.out.println("Playing the MP4 audio song");
  }
}

class VLCMediaPlayer implements AdvancedMediaPlayer {
  public void play(String filename) {
    System.out.println("Playing the VLC audio song");
  }
}

public class AdapterDesignPattern {
  public static void main(String []args) throws Exception {
    MediaPlayer mediaPlayer = new BasicPlayer();
    mediaPlayer.play(MusicType.MP3, "MP3 filename");
    mediaPlayer.play(MusicType.MP4, "MP4 filename");
    mediaPlayer.play(MusicType.VLC, "VLC filename");
  }  
}
