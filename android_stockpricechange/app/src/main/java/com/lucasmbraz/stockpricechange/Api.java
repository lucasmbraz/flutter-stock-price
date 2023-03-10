// Autogenerated from Pigeon (v7.1.4), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package com.lucasmbraz.stockpricechange;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Generated class from Pigeon. */
@SuppressWarnings({"unused", "unchecked", "CodeBlock2Expr", "RedundantSuppression"})
public class Api {
  @NonNull
  private static ArrayList<Object> wrapError(@NonNull Throwable exception) {
    ArrayList<Object> errorList = new ArrayList<Object>(3);
    errorList.add(exception.toString());
    errorList.add(exception.getClass().getSimpleName());
    errorList.add(
      "Cause: " + exception.getCause() + ", Stacktrace: " + Log.getStackTraceString(exception));
    return errorList;
  }

  public enum VisualisationType {
    TABLE(0),
    CHART(1);

    private final int index;

    private VisualisationType(final int index) {
      this.index = index;
    }
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static final class Visualisation {
    private @NonNull VisualisationType visualisationType;

    public @NonNull VisualisationType getVisualisationType() {
      return visualisationType;
    }

    public void setVisualisationType(@NonNull VisualisationType setterArg) {
      if (setterArg == null) {
        throw new IllegalStateException("Nonnull field \"visualisationType\" is null.");
      }
      this.visualisationType = setterArg;
    }

    /** Constructor is private to enforce null safety; use Builder. */
    private Visualisation() {}

    public static final class Builder {

      private @Nullable VisualisationType visualisationType;

      public @NonNull Builder setVisualisationType(@NonNull VisualisationType setterArg) {
        this.visualisationType = setterArg;
        return this;
      }

      public @NonNull Visualisation build() {
        Visualisation pigeonReturn = new Visualisation();
        pigeonReturn.setVisualisationType(visualisationType);
        return pigeonReturn;
      }
    }

    @NonNull
    ArrayList<Object> toList() {
      ArrayList<Object> toListResult = new ArrayList<Object>(1);
      toListResult.add(visualisationType == null ? null : visualisationType.index);
      return toListResult;
    }

    static @NonNull Visualisation fromList(@NonNull ArrayList<Object> list) {
      Visualisation pigeonResult = new Visualisation();
      Object visualisationType = list.get(0);
      pigeonResult.setVisualisationType(visualisationType == null ? null : VisualisationType.values()[(int) visualisationType]);
      return pigeonResult;
    }
  }

  private static class FlutterStockApiCodec extends StandardMessageCodec {
    public static final FlutterStockApiCodec INSTANCE = new FlutterStockApiCodec();

    private FlutterStockApiCodec() {}

    @Override
    protected Object readValueOfType(byte type, @NonNull ByteBuffer buffer) {
      switch (type) {
        case (byte) 128:
          return Visualisation.fromList((ArrayList<Object>) readValue(buffer));
        default:
          return super.readValueOfType(type, buffer);
      }
    }

    @Override
    protected void writeValue(@NonNull ByteArrayOutputStream stream, Object value) {
      if (value instanceof Visualisation) {
        stream.write(128);
        writeValue(stream, ((Visualisation) value).toList());
      } else {
        super.writeValue(stream, value);
      }
    }
  }

  /** Generated class from Pigeon that represents Flutter messages that can be called from Java. */
  public static final class FlutterStockApi {
    private final BinaryMessenger binaryMessenger;

    public FlutterStockApi(BinaryMessenger argBinaryMessenger) {
      this.binaryMessenger = argBinaryMessenger;
    }

    /** Public interface for sending reply. */     public interface Reply<T> {
      void reply(T reply);
    }
    /** The codec used by FlutterStockApi. */
    static MessageCodec<Object> getCodec() {
      return FlutterStockApiCodec.INSTANCE;
    }
    public void chooseVisualisationType(@NonNull Visualisation visualisationArg, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(
              binaryMessenger, "dev.flutter.pigeon.FlutterStockApi.chooseVisualisationType", getCodec());
      channel.send(
          new ArrayList<Object>(Collections.singletonList(visualisationArg)),
          channelReply -> callback.reply(null));
    }
  }
  /** Generated interface from Pigeon that represents a handler of messages from Flutter. */
  public interface HostNavigationApi {

    void goBack();

    /** The codec used by HostNavigationApi. */
    static MessageCodec<Object> getCodec() {
      return new StandardMessageCodec();
    }
    /**Sets up an instance of `HostNavigationApi` to handle messages through the `binaryMessenger`. */
    static void setup(BinaryMessenger binaryMessenger, HostNavigationApi api) {
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(
                binaryMessenger, "dev.flutter.pigeon.HostNavigationApi.goBack", getCodec());
        if (api != null) {
          channel.setMessageHandler(
              (message, reply) -> {
                ArrayList<Object> wrapped = new ArrayList<Object>();
                try {
                  api.goBack();
                  wrapped.add(0, null);
                } catch (Error | RuntimeException exception) {
                  ArrayList<Object> wrappedError = wrapError(exception);
                  wrapped = wrappedError;
                }
                reply.reply(wrapped);
              });
        } else {
          channel.setMessageHandler(null);
        }
      }
    }
  }
}
