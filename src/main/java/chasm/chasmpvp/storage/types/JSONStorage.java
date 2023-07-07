package chasm.chasmpvp.storage.types;

import chasm.chasmpvp.ChasmPvP;
import chasm.chasmpvp.objects.StorageObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public abstract class JSONStorage<I, T extends StorageObject<I>> {

    // Identifiers
    private final Gson GSON = new GsonBuilder()
            .enableComplexMapKeySerialization()
            .excludeFieldsWithModifiers(java.lang.reflect.Modifier.STATIC, java.lang.reflect.Modifier.TRANSIENT)
            .serializeNulls()
            .create();

    private final File folder;
    private final Class<T> objectClass;

    // Constructor
    public JSONStorage(Class<T> objectClass, String folderName) {
        this.objectClass = objectClass;
        this.folder = new File(ChasmPvP.getInstance().getDataFolder(), folderName);

        if (!this.folder.exists()) {
            this.folder.mkdir();
        }
    }

    public CompletableFuture<Void> saveAsync(T object) {
        return CompletableFuture.runAsync(() -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(object);
            if (json != null) {
                try {
                    File objectFile = getObjectFile(object.getIdentifier());

                    if (!objectFile.exists()) {
                        objectFile.createNewFile();
                    }

                    Files.writeString(objectFile.toPath(), json);
                } catch (IOException e) {
                    throw new CompletionException(e);
                }
            }
        }).exceptionally(ex -> {
            // Handle exception during execution
            ex.printStackTrace();
            return null;
        });
    }


    // Get the first object from the key
    public CompletableFuture<T> getObject(I identifier) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                File objectFile = getObjectFile(identifier);
                if (!objectFile.exists()) return null;

                return deserialize(Files.readString(objectFile.toPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        });
    }

    private T deserialize(String json) {
        return GSON.fromJson(json, objectClass);
    }

    private String serialize(T object) {
        return GSON.toJson(object, objectClass);
    }


    private File getObjectFile(I identifier) {
        return new File(folder, identifier + ".json");
    }
}

