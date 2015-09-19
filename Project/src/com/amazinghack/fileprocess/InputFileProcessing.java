
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;

public class InputFileProcessing {
 
    public static void main(String[] args) {
    	Map<String, Map<String, String>> outerMap = new HashMap<String, Map<String,String>>();
		String iD="";
		String aN="";
		String aV="";
		BufferedReader br = null; 
		String line = "";
		String cvsSplitBy = ",";
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            Path dir = Paths.get("C:\\Users\\Jee\\Downloads\\watching");
            dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
             
            System.out.println("Watch Service registered for dir: " + dir.getFileName());
             
            while (true) {
                WatchKey key;
                try {
                    key = watcher.take();
                } catch (InterruptedException ex) {
                    return;
                }
                 
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                     
                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();
                     
                    System.out.println(kind.name() + ": " + fileName);
                    if(kind.name().equals("ENTRY_CREATE")||kind.name().equals("ENTRY_MODIFY")){
                    	System.out.println("hi..");
                    	File f=new File("C:\\Users\\Jee\\Downloads\\watching\\"+fileName);
                    	f.setWritable(true);                   
                    	br = new BufferedReader(new FileReader("C:\\Users\\Jee\\Downloads\\watching\\"+fileName));
            			while ((line = br.readLine()) != null) {
            				iD = line.split(cvsSplitBy)[0];
            				aN = line.split(cvsSplitBy)[1];
            				aV = line.split(cvsSplitBy)[2];
            				System.out.println("hi..");
            				if(outerMap.containsKey(iD))
            					outerMap.get(iD).put(aN,aV);
            				else
            				{
            					Map<String, String> tempMap = new HashMap<String,String>();
            					tempMap.put(aN, aV);
            					outerMap.put(iD, tempMap);
            				}
            			}
            			for (String key1 : outerMap.keySet()) {	
                			Map<String, String> t=outerMap.get(key1);
                			System.out.println(key1);
                			for (String key2 : t.keySet()) {				
                			  System.out.println(t.get(key2));
                			}
                		}
                    }
                }
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }                         
            }
             
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}