package threadeg.eg7;

import java.util.*;
import java.util.concurrent.*;

/**
 * @Description: 在预定时间内请求旅游报价 《java并发编程》 P.109
 * @Auther: wangxiangyu
 * @Date: 2017/11/23 22:12
 */
public class QuoteTask implements Callable<TravelQuote> {

    private final ExecutorService exec = Executors.newFixedThreadPool(11);

    private final TravelCompany company;
    private final TravelInfo travelInfo;

    public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
        this.company = company;
        this.travelInfo = travelInfo;
    }


    @Override
    public TravelQuote call() throws Exception {
        return company.solicitQutote(travelInfo);
    }

    public List<TravelQuote> getRankedTravelQuotes(TravelInfo travelInfo, Set<TravelCompany> companies,
                                                   Comparator<TravelQuote> ranking, long time, TimeUnit unit)
        throws InterruptedException{
        List<QuoteTask> tasks = new ArrayList<>();
        for (TravelCompany company : companies){
            tasks.add(new QuoteTask(company, travelInfo));
        }

        List<Future<TravelQuote>> futures = exec.invokeAll(tasks, time, unit);
        List<TravelQuote> quotes = new ArrayList<>(tasks.size());
        Iterator<QuoteTask> taskIter = tasks.iterator();
        for (Future<TravelQuote> f : futures){
            QuoteTask task = taskIter.next();
            try {
                quotes.add(f.get());
            }catch (ExecutionException e){
                //quotes.add(task.getFailureQuote(e.getCause()));
            }catch (CancellationException e){
                //quotes.add(task.getTimeoutQuote(e));
            }
        }
        Collections.sort(quotes, ranking);
        return quotes;
    }
}

class TravelQuote{

}
class TravelCompany{

    public TravelQuote solicitQutote(TravelInfo travelInfo){

        return new TravelQuote();
    }

}
class TravelInfo{

}

