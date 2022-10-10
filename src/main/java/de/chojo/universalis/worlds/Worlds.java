/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.worlds;

import de.chojo.universalis.entities.shared.World;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Worlds {
    private static final Map<Integer, World> ids = new HashMap<>();
    private static final Europe europe = new Europe();
    private static final NorthAmerica northAmerica = new NorthAmerica();
    private static final Oceania oceania = new Oceania();
    private static final Japan japan = new Japan();

    public static Europe europe() {
        return europe;
    }

    public static NorthAmerica northAmerica() {
        return northAmerica;
    }

    public static Oceania oceania() {
        return oceania;
    }

    public static Japan japan() {
        return japan;
    }

    public static List<Region> regions() {
        return List.of(europe, northAmerica, oceania, japan);
    }

    public static World byId(int id) {
        return ids.computeIfAbsent(id, Worlds::findById);
    }

    private static World findById(int id) {
        for (Region region : regions()) {
            for (World world : region.worlds()) {
                if (world.id() == id) return world;
            }
        }
        return null;
    }

    public static final class Europe implements Region {
        private final Chaos chaos = new Chaos();
        private final Light light = new Light();

        private Europe() {
        }

        public Chaos chaos() {
            return chaos;
        }

        public Light light() {
            return light;
        }

        @Override
        public List<Datacenter> datacenters() {
            return List.of(chaos, light);
        }

        public static final class Chaos implements Datacenter {
            public final World CERBERUS = World.of("Cerberus", 80);
            public final World LOUISOIX = World.of("Louisoix", 83);
            public final World MOOGLE = World.of("Moogle", 71);
            public final World OMEGA = World.of("Omega", 39);
            public final World PHANTOM = World.of("Phantom", 401);
            public final World RAGNAROK = World.of("Ragnarok", 97);
            public final World SAGITTARIUS = World.of("Sagittarius", 400);
            public final World SPRIGGAN = World.of("Spriggan", 85);

            private Chaos() {
            }

            @Override
            public List<World> worlds() {
                return List.of(CERBERUS, LOUISOIX, MOOGLE, OMEGA, PHANTOM, RAGNAROK, SAGITTARIUS, SPRIGGAN);
            }
        }

        public static final class Light implements Datacenter {
            public final World ALPHA = World.of("Alpha", 402);
            public final World LICH = World.of("Lich", 36);
            public final World ODIN = World.of("Odin", 66);
            public final World PHOENIX = World.of("Phoenix", 56);
            public final World RAIDEN = World.of("Raiden", 403);
            public final World SHIVA = World.of("Shiva", 67);
            public final World TWINTANIA = World.of("Twintania", 33);
            public final World ZODIARK = World.of("Zodiark", 42);

            private Light() {
            }

            @Override
            public List<World> worlds() {
                return List.of(ALPHA, LICH, ODIN, PHOENIX, RAIDEN, SHIVA, TWINTANIA, ZODIARK);
            }
        }
    }

    public static final class NorthAmerica implements Region {
        private final Aether aether = new Aether();
        private final Crystal crystal = new Crystal();
        private final Primal primal = new Primal();

        private NorthAmerica() {
        }

        public Aether aether() {
            return aether;
        }

        public Crystal crystal() {
            return crystal;
        }

        public Primal primal() {
            return primal;
        }

        @Override
        public List<Datacenter> datacenters() {
            return List.of(aether, crystal, primal);
        }

        public static final class Aether implements Datacenter {
            public final World ADAMANTOISE = World.of("Adamantoise", 73);
            public final World CACTUAR = World.of("Cactuar", 79);
            public final World FAERIE = World.of("Faerie", 54);
            public final World GILGAMESH = World.of("Gilgamesh", 63);
            public final World JENOVA = World.of("Jenova", 40);
            public final World MIDGARDSORMR = World.of("Midgardsormr", 65);
            public final World SARGATANAS = World.of("Sargatanas", 99);
            public final World SIREN = World.of("Siren", 57);

            private Aether() {
            }

            @Override
            public List<World> worlds() {
                return List.of(ADAMANTOISE, CACTUAR, FAERIE, GILGAMESH, JENOVA, MIDGARDSORMR, SARGATANAS, SIREN);
            }
        }

        public static final class Crystal implements Datacenter {
            public final World BALMUNG = World.of("Balmung", 91);
            public final World BRYNHILDR = World.of("Brynhildr", 34);
            public final World COEURL = World.of("Coeurl", 74);
            public final World DIABOLOS = World.of("Diabolos", 62);
            public final World GOBLIN = World.of("Goblin", 81);
            public final World MALBORO = World.of("Malboro", 75);
            public final World MATEUS = World.of("Mateus", 37);
            public final World ZALERA = World.of("Zalera", 41);

            private Crystal() {
            }

            @Override
            public List<World> worlds() {
                return List.of(BALMUNG, BRYNHILDR, COEURL, DIABOLOS, GOBLIN, MALBORO, MATEUS, ZALERA);
            }
        }

        public static final class Primal implements Datacenter {
            public final World BEHEMOTH = World.of("Behemoth", 78);
            public final World EXCALIBUR = World.of("Excalibur", 93);
            public final World EXODUS = World.of("Exodus", 53);
            public final World FAMFRIT = World.of("Famfrit", 35);
            public final World HYPERION = World.of("Hyperion", 95);
            public final World LAMIA = World.of("Lamia", 55);
            public final World LEVIATHAN = World.of("Leviathan", 64);
            public final World ULTROS = World.of("Ultros", 77);

            private Primal() {
            }

            @Override
            public List<World> worlds() {
                return List.of(BEHEMOTH, EXCALIBUR, EXODUS, FAMFRIT, HYPERION, LAMIA, LEVIATHAN, ULTROS);
            }
        }
    }

    public static final class Oceania implements Region {
        private final Materia materia = new Materia();

        private Oceania() {
        }

        public Materia materia() {
            return materia;
        }

        @Override
        public List<Datacenter> datacenters() {
            return Collections.singletonList(materia);
        }

        public static final class Materia implements Datacenter {
            public final World BISMARCK = World.of("Bismarck", 22);
            public final World RAVANA = World.of("Ravana", 21);
            public final World SEPHIROT = World.of("Sephirot", 86);
            public final World SOPHIA = World.of("Sophia", 87);
            public final World ZURVAN = World.of("Zurvan", 88);

            private Materia() {
            }

            @Override
            public List<World> worlds() {
                return List.of(BISMARCK, RAVANA, SEPHIROT, SOPHIA, ZURVAN);
            }
        }
    }

    public static final class Japan implements Region {
        private final Elemental elemental = new Elemental();
        private final Gaia gaia = new Gaia();
        private final Mana mana = new Mana();
        private final Meteor meteor = new Meteor();

        private Japan() {
        }

        @Override
        public List<Datacenter> datacenters() {
            return List.of(elemental, gaia, mana, meteor);
        }

        public Elemental elemental() {
            return elemental;
        }

        public Gaia gaia() {
            return gaia;
        }

        public Mana mana() {
            return mana;
        }

        public Meteor meteor() {
            return meteor;
        }

        public static final class Elemental implements Datacenter {
            public final World AEGIS = World.of("Aegis", 90);
            public final World ATOMOS = World.of("Atomos", 68);
            public final World CARBUNCLE = World.of("Carbuncle", 45);
            public final World GARUDA = World.of("Garuda", 58);
            public final World GUNGNIR = World.of("Gungnir", 94);
            public final World KUJATA = World.of("Kujata", 49);
            public final World TONBERRY = World.of("Tonberry", 72);
            public final World TYPHON = World.of("Typhon", 50);

            private Elemental() {
            }

            @Override
            public List<World> worlds() {
                return List.of(AEGIS, ATOMOS, CARBUNCLE, GARUDA, GUNGNIR, KUJATA, TONBERRY, TYPHON);
            }
        }

        public static final class Gaia implements Datacenter {
            public final World ALEXANDER = World.of("Alexander", 43);
            public final World BAHAMUT = World.of("Bahamut", 69);
            public final World DURANDAL = World.of("Durandal", 92);
            public final World FENRIR = World.of("Fenrir", 46);
            public final World IFRIT = World.of("Ifrit", 59);
            public final World RIDILL = World.of("Ridill", 98);
            public final World TIAMAT = World.of("Tiamat", 76);
            public final World ULTIMA = World.of("Ultima", 51);

            private Gaia() {
            }

            @Override
            public List<World> worlds() {
                return List.of(ALEXANDER, BAHAMUT, DURANDAL, FENRIR, IFRIT, RIDILL, TIAMAT, ULTIMA);
            }
        }

        public static final class Mana implements Datacenter {
            public final World ANIMA = World.of("Anima", 44);
            public final World ASURA = World.of("Asura", 23);
            public final World CHOCOBO = World.of("Chocobo", 70);
            public final World HADES = World.of("Hades", 47);
            public final World IXION = World.of("Ixion", 48);
            public final World MASAMUNE = World.of("Masamune", 96);
            public final World PANDAEMONIUM = World.of("Pandaemonium", 28);
            public final World TITAN = World.of("Titan", 61);

            private Mana() {
            }

            @Override
            public List<World> worlds() {
                return List.of(ANIMA, ASURA, CHOCOBO, HADES, IXION, MASAMUNE, PANDAEMONIUM, TITAN);
            }
        }

        public static final class Meteor implements Datacenter {
            public final World BELIAS = World.of("Belias", 24);
            public final World MANDRAGORA = World.of("Mandragora", 82);
            public final World RAMUH = World.of("Ramuh", 60);
            public final World SHINRYU = World.of("Shinryu", 29);
            public final World UNICORN = World.of("Unicorn", 30);
            public final World VALEFOR = World.of("Valefor", 52);
            public final World YOJIMBO = World.of("Yojimbo", 31);
            public final World ZEROMUS = World.of("Zeromus", 32);

            private Meteor() {
            }

            @Override
            public List<World> worlds() {
                return List.of(BELIAS, MANDRAGORA, RAMUH, SHINRYU, UNICORN, VALEFOR, YOJIMBO, ZEROMUS);
            }
        }
    }
}
