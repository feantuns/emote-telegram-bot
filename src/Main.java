import java.util.List;
import java.util.Random;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendAnimation;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchFeed;
import at.mukprojects.giphy4j.exception.GiphyException;

public class Main {

	public static void main(String[] args) {

		TelegramBot bot = new TelegramBot("TELEGRAM_API_KEY");

		GetUpdatesResponse updatesResponse;
		
		List<Update> updatesList;
		
		String messageReceived;
		
		Giphy giphy = new Giphy("GIPHY_API_KEY");
		
		SearchFeed feed;
		
		int numberOfGifs = 10;
		
		Random rand = new Random(); 
		
		boolean isEmoticon;
		
		int offset = 0;

		while (true) {
			
			updatesResponse =  bot.execute(new GetUpdates().limit(100).offset(offset));
			
			updatesList = updatesResponse.updates();

			if (updatesList != null) {
				
				for (Update update : updatesList) {
					
					offset = update.updateId()+1;
					
					messageReceived = update.message().text();
					
					isEmoticon = Character.UnicodeBlock.EMOTICONS.equals(Character.UnicodeBlock.of(messageReceived.codePointAt(0)));
					
					if (isEmoticon) {
						
						try {
							
							feed = giphy.search(messageReceived, numberOfGifs, 0);
							
							bot.execute(
									new SendChatAction(
											update.message().chat().id(), 
											ChatAction.typing.name()
									)
							);
							
							bot.execute(
									new SendAnimation(
											update.message().chat().id(), 
											feed.getDataList().get(rand.nextInt(numberOfGifs)).getImages().getOriginal().getUrl()
									)
							);
						
						} catch (GiphyException e) {
							
							e.printStackTrace();
							
						}
						
					} else {

						bot.execute(
								new SendMessage(
										update.message().chat().id(),
										"Envie um emote 🙂 para receber um GIF."
								)
						);
					
					}
				
				}
				
			}

		}

	}

}
