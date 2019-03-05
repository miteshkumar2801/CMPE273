package hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    private static final String template = "This is Get/READ (1) Client, %s!";
    private final AtomicLong counter = new AtomicLong();
    HashMap<Long,String> hashM = new HashMap<Long, String>();

    @RequestMapping(value = "/greeting",method = RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name", defaultValue=":: GET") String name) {
        long id = counter.incrementAndGet();
        hashM.put(id,name);
        return new Greeting(id, String.format(template, name));
    }

    @RequestMapping(value = "/greeting",method = RequestMethod.POST)
    public @ResponseBody Greeting update (@RequestParam (value="id") long id,@RequestParam (value="data") String data)  {
    //    public @ResponseBody Greeting update (@RequestParam ArrayList<String> name)  {
      //  long id = Long.parseLong("name[0]");
       // String value = "name[1]";

        if (!hashM.containsKey(id)) {
            hashM.put(id,data);
        } else {
            System.out.println("Key Exists");
            hashM.put(id,data);
        }
        return new Greeting(id,data);


    }
}
