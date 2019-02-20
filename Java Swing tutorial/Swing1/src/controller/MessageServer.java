package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import model.Message;

public class MessageServer implements Iterable<Message> {

	private Map<Integer, List<Message>> messages;
	private List<Message> selected;
	
	
	public MessageServer() {
		selected = new ArrayList<Message>();
		messages = new TreeMap<Integer, List<Message>>();
		
		List<Message> list = new ArrayList<Message>();
		list.add(new Message("title1","messsage no 1"));
		list.add(new Message("title2","messsage no 2"));	
		messages.put(0, list);
		
		
		list = new ArrayList<Message>();
		list.add(new Message("title3","messsage no 3"));
		
		messages.put(1, list);	
		
	}
	
	
	public void setSelectedServers(Set<Integer> servers) {
		
		selected.clear();
		
		for(Integer id: servers) {
			if(messages.containsKey(id)) {
				selected.addAll(messages.get(id));
			}
		}
		
	}

	
	public int getMessageCount() {
		return selected.size();
	}


	@Override
	public Iterator<Message> iterator() {
		
		return new MessageIterator(selected);
	}
	
}

class MessageIterator implements Iterator {

	
	private Iterator<Message> iterator;
	
	
	public MessageIterator(List<Message> messages) {
		iterator = messages.iterator();
	}
	
	
	@Override
	public boolean hasNext() {
		
		return iterator.hasNext();
	}

	@Override
	public Object next() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iterator.next();
	}

	@Override
	public void remove() {
		
		iterator.remove();
	}
	
	
	
	
}
