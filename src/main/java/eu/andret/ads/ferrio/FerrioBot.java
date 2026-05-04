package eu.andret.ads.ferrio;

import eu.andret.ads.ferrio.command.FerrioCommand;
import eu.andret.ads.ferrio.util.Requestor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

public final class FerrioBot {
	private static final Requestor REQUESTOR = new Requestor();

	public static void main(final String[] args) throws IOException {
		final Properties properties = loadProperties();
		Configurator.setRootLevel(Level.toLevel(properties.getProperty("logger.level"), Level.INFO));

		final JDA jda = JDABuilder.createLight(properties.getProperty("app.token"), Collections.emptyList())
				.setStatus(OnlineStatus.DO_NOT_DISTURB)
				.addEventListeners(new FerrioCommand(REQUESTOR))
				.build();

		jda.updateCommands()
				.addCommands(
						Commands
								.slash("ferrio", "Get random today holiday")
								.addOptions(new OptionData(OptionType.STRING, "language", "Language of the holiday", false)
										.addChoice("Polish", "pl")
										.addChoice("English", "en"))
								.setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.VIEW_CHANNEL)))
				.queue();
	}

	@NotNull
	private static Properties loadProperties() throws IOException {
		final InputStream config = ClassLoader.getSystemClassLoader().getResourceAsStream("config.properties");
		final Properties properties = new Properties();
		properties.load(config);
		return properties;
	}
}
