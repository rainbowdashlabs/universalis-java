/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.worlds;

import org.jetbrains.annotations.Contract;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Class containing the currently valid regions, datacenters and worlds.
 */
public class Worlds {
    private static final Map<Integer, World> ids = new HashMap<>();
    private static final Map<String, World> names = new HashMap<>();
    private static final Europe europe = new Europe();
    private static final NorthAmerica northAmerica = new NorthAmerica();
    private static final Oceania oceania = new Oceania();
    private static final Japan japan = new Japan();
    private static final 中国 中国 = new 中国();

    /**
     * The european region
     *
     * @return europe region
     */
    public static Europe europe() {
        return europe;
    }

    /**
     * The north american region
     *
     * @return north american region
     */
    public static NorthAmerica northAmerica() {
        return northAmerica;
    }

    /**
     * The oceanian region
     *
     * @return oceanian region
     */
    public static Oceania oceania() {
        return oceania;
    }

    /**
     * The japanese region
     *
     * @return japanese region
     */
    public static Japan japan() {
        return japan;
    }

    /**
     * The 中国 region
     *
     * @return 中国 region
     */
    public static 中国 中国() {
        return 中国;
    }

    /**
     * Returns all valid regions
     *
     * @return unmodifiable list of regions
     */
    public static List<Region> regions() {
        return List.of(europe, northAmerica, oceania, japan);
    }

    /**
     * Gets a world by its id.
     *
     * @param id world id
     * @return world if valid or a dummy world with the matching ID
     */
    public static World worldById(int id) {
        return ids.computeIfAbsent(id, Worlds::findWorldById);
    }

    /**
     * Retrieves a data center by name
     *
     * @param name data center name case-insensitive
     * @return data center
     */
    @Nullable
    @Contract("null -> null")
    public static DataCenter datacenterByName(@Nullable String name) {
        for (Region region : regions()) {
            for (DataCenter datacenter : region.datacenters()) {
                if (datacenter.name().equalsIgnoreCase(name)) return datacenter;
            }
        }
        return null;
    }

    /**
     * Retrieves a region by name
     *
     * @param name region name case-insensitive
     * @return region
     */
    @Nullable
    @Contract("null -> null")
    public static Region regionByName(@Nullable String name) {
        for (Region region : regions()) {
            if (region.name().equalsIgnoreCase(name)) return region;
        }
        return null;
    }

    private static World findWorldById(int id) {
        for (Region region : regions()) {
            for (World world : region.worlds()) {
                if (world.id() == id) return world;
            }
        }
        for (World value : names.values()) {
            if (value.id() == id) return value;
        }
        return World.of("", id);
    }

    /**
     * Retrieve a world by its name
     *
     * @param name name case-insensitive
     * @return world
     */
    @Nullable
    @Contract("null -> null")
    public static World worldByName(@Nullable String name) {
        if (name == null) return null;
        return names.computeIfAbsent(name.toLowerCase(), Worlds::findWorldByName);
    }

    private static World findWorldByName(String name) {
        for (Region region : regions()) {
            for (World world : region.worlds()) {
                if (world.name().equalsIgnoreCase(name)) return world;
            }
        }
        for (World world : ids.values()) {
            if (world.name().equalsIgnoreCase(name)) return world;
        }
        return World.of(name, -1);
    }

    /**
     * Transforms a world entity to an internal richer world model
     *
     * @param world world entity
     * @return world
     */
    public static World toWorld(de.chojo.universalis.entities.World world) {
        World iWorld = World.of(world.name(), world.id());
        World worldByName = findWorldByName(world.name());
        if (worldByName.id() != -1) {
            iWorld = worldByName;
        }

        World worldById = findWorldById(world.id());
        if (!worldById.name().isBlank()) {
            iWorld = worldById;
        }

        if (worldById.name().isBlank()) {
            ids.put(iWorld.id(), iWorld);
        }

        if (worldByName.id() == -1) {
            names.put(iWorld.name().toLowerCase(Locale.ROOT), iWorld);
        }

        return iWorld;
    }

    /**
     * The european region
     */
    public static final class Europe implements Region {
        private final Chaos chaos = new Chaos();
        private final Light light = new Light();

        private Europe() {
        }

        /**
         * The chaosdate center
         *
         * @return chaosdate center
         */
        public Chaos chaos() {
            return chaos;
        }

        /**
         * The lightdate center
         *
         * @return lightdate center
         */
        public Light light() {
            return light;
        }

        @Override
        public List<DataCenter> datacenters() {
            return List.of(chaos, light);
        }

        @Override
        public String name() {
            return "Europe";
        }

        /**
         * The chaosdate center
         */
        public static final class Chaos implements DataCenter {
            /**
             * The world Cerberus
             */
            public final World CERBERUS = World.of("Cerberus", 80);
            /**
             * The world Louisoix
             */
            public final World LOUISOIX = World.of("Louisoix", 83);
            /**
             * The world Moogle
             */
            public final World MOOGLE = World.of("Moogle", 71);
            /**
             * The world Omega
             */
            public final World OMEGA = World.of("Omega", 39);
            /**
             * The world Phantom
             */
            public final World PHANTOM = World.of("Phantom", 401);
            /**
             * The world Ragnarok
             */
            public final World RAGNAROK = World.of("Ragnarok", 97);
            /**
             * The world Sagittarius
             */
            public final World SAGITTARIUS = World.of("Sagittarius", 400);
            /**
             * The world Spriggan
             */
            public final World SPRIGGAN = World.of("Spriggan", 85);

            private Chaos() {
            }

            @Override
            public List<World> worlds() {
                return List.of(CERBERUS, LOUISOIX, MOOGLE, OMEGA, PHANTOM, RAGNAROK, SAGITTARIUS, SPRIGGAN);
            }

            @Override
            public int id() {
                return 6;
            }
        }

        /**
         * The lightdate center
         */
        public static final class Light implements DataCenter {
            /**
             * The world Alpha
             */
            public final World ALPHA = World.of("Alpha", 402);
            /**
             * The world Lich
             */
            public final World LICH = World.of("Lich", 36);
            /**
             * The world Odin
             */
            public final World ODIN = World.of("Odin", 66);
            /**
             * The world Phoenix
             */
            public final World PHOENIX = World.of("Phoenix", 56);
            /**
             * The world Raiden
             */
            public final World RAIDEN = World.of("Raiden", 403);
            /**
             * The world Shiva
             */
            public final World SHIVA = World.of("Shiva", 67);
            /**
             * The world Twintania
             */
            public final World TWINTANIA = World.of("Twintania", 33);
            /**
             * The world Zodiark
             */
            public final World ZODIARK = World.of("Zodiark", 42);

            private Light() {
            }

            @Override
            public List<World> worlds() {
                return List.of(ALPHA, LICH, ODIN, PHOENIX, RAIDEN, SHIVA, TWINTANIA, ZODIARK);
            }

            @Override
            public int id() {
                return 7;
            }
        }
    }

    /**
     * The north american region
     */
    public static final class NorthAmerica implements Region {
        private final Aether aether = new Aether();
        private final Crystal crystal = new Crystal();
        private final Primal primal = new Primal();

        private NorthAmerica() {
        }

        /**
         * The aetherdate center
         *
         * @return aetherdate center
         */
        public Aether aether() {
            return aether;
        }

        /**
         * The crystaldate center
         *
         * @return crystaldate center
         */
        public Crystal crystal() {
            return crystal;
        }

        /**
         * The primaldate center
         *
         * @return primaldate center
         */
        public Primal primal() {
            return primal;
        }

        @Override
        public List<DataCenter> datacenters() {
            return List.of(aether, crystal, primal);
        }

        @Override
        public String name() {
            return "North-America";
        }

        /**
         * The aetherdate center
         */
        public static final class Aether implements DataCenter {
            /**
             * The world Adamantoise
             */
            public final World ADAMANTOISE = World.of("Adamantoise", 73);
            /**
             * The world Cactuar
             */
            public final World CACTUAR = World.of("Cactuar", 79);
            /**
             * The world Faerie
             */
            public final World FAERIE = World.of("Faerie", 54);
            /**
             * The world Gilgamesh
             */
            public final World GILGAMESH = World.of("Gilgamesh", 63);
            /**
             * The world Jenova
             */
            public final World JENOVA = World.of("Jenova", 40);
            /**
             * The world Midgardsormr
             */
            public final World MIDGARDSORMR = World.of("Midgardsormr", 65);
            /**
             * The world Sargatanas
             */
            public final World SARGATANAS = World.of("Sargatanas", 99);
            /**
             * The world Siren
             */
            public final World SIREN = World.of("Siren", 57);

            private Aether() {
            }

            @Override
            public List<World> worlds() {
                return List.of(ADAMANTOISE, CACTUAR, FAERIE, GILGAMESH, JENOVA, MIDGARDSORMR, SARGATANAS, SIREN);
            }

            @Override
            public int id() {
                return 4;
            }
        }

        /**
         * The crystaldate center
         */
        public static final class Crystal implements DataCenter {
            /**
             * The world Balmung
             */
            public final World BALMUNG = World.of("Balmung", 91);
            /**
             * The world Brynhildr
             */
            public final World BRYNHILDR = World.of("Brynhildr", 34);
            /**
             * The world Coeurl
             */
            public final World COEURL = World.of("Coeurl", 74);
            /**
             * The world Diabolos
             */
            public final World DIABOLOS = World.of("Diabolos", 62);
            /**
             * The world Goblin
             */
            public final World GOBLIN = World.of("Goblin", 81);
            /**
             * The world Malboro
             */
            public final World MALBORO = World.of("Malboro", 75);
            /**
             * The world Mateus
             */
            public final World MATEUS = World.of("Mateus", 37);
            /**
             * The world Zalera
             */
            public final World ZALERA = World.of("Zalera", 41);

            private Crystal() {
            }

            @Override
            public List<World> worlds() {
                return List.of(BALMUNG, BRYNHILDR, COEURL, DIABOLOS, GOBLIN, MALBORO, MATEUS, ZALERA);
            }

            @Override
            public int id() {
                return 8;
            }
        }

        /**
         * The primaldate center
         */
        public static final class Primal implements DataCenter {
            /**
             * The world Behemoth
             */
            public final World BEHEMOTH = World.of("Behemoth", 78);
            /**
             * The world Excalibur
             */
            public final World EXCALIBUR = World.of("Excalibur", 93);
            /**
             * The world Exodus
             */
            public final World EXODUS = World.of("Exodus", 53);
            /**
             * The world Famfrit
             */
            public final World FAMFRIT = World.of("Famfrit", 35);
            /**
             * The world Hyperion
             */
            public final World HYPERION = World.of("Hyperion", 95);
            /**
             * The world Lamia
             */
            public final World LAMIA = World.of("Lamia", 55);
            /**
             * The world Leviathan
             */
            public final World LEVIATHAN = World.of("Leviathan", 64);
            /**
             * The world Ultros
             */
            public final World ULTROS = World.of("Ultros", 77);

            private Primal() {
            }

            @Override
            public List<World> worlds() {
                return List.of(BEHEMOTH, EXCALIBUR, EXODUS, FAMFRIT, HYPERION, LAMIA, LEVIATHAN, ULTROS);
            }

            @Override
            public int id() {
                return 5;
            }
        }
    }

    /**
     * The oceanian region
     */
    public static final class Oceania implements Region {
        private final Materia materia = new Materia();

        private Oceania() {
        }

        /**
         * The materiadate center
         *
         * @return materiadate center
         */
        public Materia materia() {
            return materia;
        }

        @Override
        public List<DataCenter> datacenters() {
            return Collections.singletonList(materia);
        }

        @Override
        public String name() {
            return "Oceania";
        }

        /**
         * The materiadate center
         */
        public static final class Materia implements DataCenter {
            /**
             * The world Bismarck
             */
            public final World BISMARCK = World.of("Bismarck", 22);
            /**
             * The world Ravana
             */
            public final World RAVANA = World.of("Ravana", 21);
            /**
             * The world Sephirot
             */
            public final World SEPHIROT = World.of("Sephirot", 86);
            /**
             * The world Sophia
             */
            public final World SOPHIA = World.of("Sophia", 87);
            /**
             * The world Zurvan
             */
            public final World ZURVAN = World.of("Zurvan", 88);

            private Materia() {
            }

            @Override
            public List<World> worlds() {
                return List.of(BISMARCK, RAVANA, SEPHIROT, SOPHIA, ZURVAN);
            }

            @Override
            public int id() {
                return 9;
            }
        }
    }

    /**
     * The japanese region
     */
    public static final class Japan implements Region {
        private final Elemental elemental = new Elemental();
        private final Gaia gaia = new Gaia();
        private final Mana mana = new Mana();
        private final Meteor meteor = new Meteor();

        private Japan() {
        }

        @Override
        public List<DataCenter> datacenters() {
            return List.of(elemental, gaia, mana, meteor);
        }

        @Override
        public String name() {
            return "Japan";
        }


        /**
         * The elementaldate center
         *
         * @return elementaldate center
         */
        public Elemental elemental() {
            return elemental;
        }

        /**
         * The gaiadate center
         *
         * @return gaiadate center
         */
        public Gaia gaia() {
            return gaia;
        }

        /**
         * The manadate center
         *
         * @return manadate center
         */
        public Mana mana() {
            return mana;
        }

        /**
         * The meteordate center
         *
         * @return meteordate center
         */
        public Meteor meteor() {
            return meteor;
        }

        /**
         * The elementaldate center
         */
        public static final class Elemental implements DataCenter {
            /**
             * The world Aegis
             */
            public final World AEGIS = World.of("Aegis", 90);
            /**
             * The world Atomos
             */
            public final World ATOMOS = World.of("Atomos", 68);
            /**
             * The world Carbuncle
             */
            public final World CARBUNCLE = World.of("Carbuncle", 45);
            /**
             * The world Garuda
             */
            public final World GARUDA = World.of("Garuda", 58);
            /**
             * The world Gungnir
             */
            public final World GUNGNIR = World.of("Gungnir", 94);
            /**
             * The world Kujata
             */
            public final World KUJATA = World.of("Kujata", 49);
            /**
             * The world Tonberry
             */
            public final World TONBERRY = World.of("Tonberry", 72);
            /**
             * The world Typhon
             */
            public final World TYPHON = World.of("Typhon", 50);

            private Elemental() {
            }

            @Override
            public List<World> worlds() {
                return List.of(AEGIS, ATOMOS, CARBUNCLE, GARUDA, GUNGNIR, KUJATA, TONBERRY, TYPHON);
            }

            @Override
            public int id() {
                return 1;
            }
        }

        /**
         * The gaiadate center
         */
        public static final class Gaia implements DataCenter {
            /**
             * The world Alexander
             */
            public final World ALEXANDER = World.of("Alexander", 43);
            /**
             * The world Bahamut
             */
            public final World BAHAMUT = World.of("Bahamut", 69);
            /**
             * The world Durandal
             */
            public final World DURANDAL = World.of("Durandal", 92);
            /**
             * The world Fenrir
             */
            public final World FENRIR = World.of("Fenrir", 46);
            /**
             * The world Ifrit
             */
            public final World IFRIT = World.of("Ifrit", 59);
            /**
             * The world Ridill
             */
            public final World RIDILL = World.of("Ridill", 98);
            /**
             * The world Tiamat
             */
            public final World TIAMAT = World.of("Tiamat", 76);
            /**
             * The world Ultima
             */
            public final World ULTIMA = World.of("Ultima", 51);

            private Gaia() {
            }

            @Override
            public List<World> worlds() {
                return List.of(ALEXANDER, BAHAMUT, DURANDAL, FENRIR, IFRIT, RIDILL, TIAMAT, ULTIMA);
            }

            @Override
            public int id() {
                return 2;
            }
        }

        /**
         * The manadate center
         */
        public static final class Mana implements DataCenter {
            /**
             * The world Anima
             */
            public final World ANIMA = World.of("Anima", 44);
            /**
             * The world Asura
             */
            public final World ASURA = World.of("Asura", 23);
            /**
             * The world Chocobo
             */
            public final World CHOCOBO = World.of("Chocobo", 70);
            /**
             * The world Hades
             */
            public final World HADES = World.of("Hades", 47);
            /**
             * The world Ixion
             */
            public final World IXION = World.of("Ixion", 48);
            /**
             * The world Masamune
             */
            public final World MASAMUNE = World.of("Masamune", 96);
            /**
             * The world Pandaemonium
             */
            public final World PANDAEMONIUM = World.of("Pandaemonium", 28);
            /**
             * The world Titan
             */
            public final World TITAN = World.of("Titan", 61);

            private Mana() {
            }

            @Override
            public List<World> worlds() {
                return List.of(ANIMA, ASURA, CHOCOBO, HADES, IXION, MASAMUNE, PANDAEMONIUM, TITAN);
            }

            @Override
            public int id() {
                return 3;
            }
        }

        /**
         * The elementaldate center
         */
        public static final class Meteor implements DataCenter {
            /**
             * The world Belias
             */
            public final World BELIAS = World.of("Belias", 24);
            /**
             * The world Mandragora
             */
            public final World MANDRAGORA = World.of("Mandragora", 82);
            /**
             * The world Ramuh
             */
            public final World RAMUH = World.of("Ramuh", 60);
            /**
             * The world Shinryu
             */
            public final World SHINRYU = World.of("Shinryu", 29);
            /**
             * The world Unicorn
             */
            public final World UNICORN = World.of("Unicorn", 30);
            /**
             * The world Valefor
             */
            public final World VALEFOR = World.of("Valefor", 52);
            /**
             * The world Yojimbo
             */
            public final World YOJIMBO = World.of("Yojimbo", 31);
            /**
             * The world Zeromus
             */
            public final World ZEROMUS = World.of("Zeromus", 32);

            private Meteor() {
            }

            @Override
            public List<World> worlds() {
                return List.of(BELIAS, MANDRAGORA, RAMUH, SHINRYU, UNICORN, VALEFOR, YOJIMBO, ZEROMUS);
            }

            @Override
            public int id() {
                return 10;
            }
        }
    }

    /**
     * The 中国 region
     */
    public static class 中国 implements Region {
        private final 陆行鸟 陆行鸟 = new 陆行鸟();
        private final 莫古力 莫古力 = new 莫古力();
        private final 猫小胖 猫小胖 = new 猫小胖();
        private final 豆豆柴 豆豆柴 = new 豆豆柴();

        @Override
        public List<DataCenter> datacenters() {
            return List.of(陆行鸟, 莫古力, 猫小胖, 豆豆柴);
        }

        @Override
        public String name() {
            return "中国";
        }

        /**
         * The 陆行鸟date center
         *
         * @return 陆行鸟date center
         */
        public 陆行鸟 陆行鸟() {
            return 陆行鸟;
        }

        /**
         * The 莫古力date center
         *
         * @return 莫古力date center
         */
        public 莫古力 莫古力() {
            return 莫古力;
        }

        /**
         * The 猫小胖date center
         *
         * @return 猫小胖date center
         */
        public 猫小胖 猫小胖() {
            return 猫小胖;
        }

        /**
         * The 豆豆柴date center
         *
         * @return 豆豆柴date center
         */
        public 豆豆柴 豆豆柴() {
            return 豆豆柴;
        }

        /**
         * The 陆行鸟date center
         */
        public static final class 陆行鸟 implements DataCenter {
            /**
             * The world 红玉海
             */
            public final World 红玉海 = World.of("红玉海", 1167);
            /**
             * The world 神意之地
             */
            public final World 神意之地 = World.of("神意之地", 1081);
            /**
             * The world 拉诺西亚
             */
            public final World 拉诺西亚 = World.of("拉诺西亚", 1042);
            /**
             * The world 幻影群岛
             */
            public final World 幻影群岛 = World.of("幻影群岛", 1044);
            /**
             * The world 萌芽池
             */
            public final World 萌芽池 = World.of("萌芽池", 1060);
            /**
             * The world 宇宙和音
             */
            public final World 宇宙和音 = World.of("宇宙和音", 1173);
            /**
             * The world 沃仙曦染
             */
            public final World 沃仙曦染 = World.of("沃仙曦染", 1174);
            /**
             * The world 晨曦王座
             */
            public final World 晨曦王座 = World.of("晨曦王座", 1175);

            @Override
            public List<World> worlds() {
                return List.of(红玉海, 神意之地, 拉诺西亚, 幻影群岛, 萌芽池, 宇宙和音, 沃仙曦染, 晨曦王座);
            }

            @Override
            public int id() {
                return -1;
            }

        }

        /**
         * The 莫古力date center
         */
        public static final class 莫古力 implements DataCenter {
            /**
             * The world 白银乡
             */
            public final World 白银乡 = World.of("白银乡", 1172);
            /**
             * The world 白金幻象
             */
            public final World 白金幻象 = World.of("白金幻象", 1076);
            /**
             * The world 神拳痕
             */
            public final World 神拳痕 = World.of("神拳痕", 1171);
            /**
             * The world 潮风亭
             */
            public final World 潮风亭 = World.of("潮风亭", 1170);
            /**
             * The world 旅人栈桥
             */
            public final World 旅人栈桥 = World.of("旅人栈桥", 1113);
            /**
             * The world 拂晓之间
             */
            public final World 拂晓之间 = World.of("拂晓之间", 1121);
            /**
             * The world 龙巢神殿
             */
            public final World 龙巢神殿 = World.of("龙巢神殿", 1166);
            /**
             * The world 梦羽宝境
             */
            public final World 梦羽宝境 = World.of("梦羽宝境", 1176);

            @Override
            public List<World> worlds() {
                return List.of(白银乡, 白金幻象, 神拳痕, 潮风亭, 旅人栈桥, 拂晓之间, 龙巢神殿, 梦羽宝境);
            }

            @Override
            public int id() {
                return -1;
            }

        }

        /**
         * The 猫小胖date center
         */
        public static final class 猫小胖 implements DataCenter {
            /**
             * The world 紫水栈桥
             */
            public final World 紫水栈桥 = World.of("紫水栈桥", 1043);
            /**
             * The world 延夏
             */
            public final World 延夏 = World.of("延夏", 1169);
            /**
             * The world 静语庄园
             */
            public final World 静语庄园 = World.of("静语庄园", 1106);
            /**
             * The world 摩杜纳
             */
            public final World 摩杜纳 = World.of("摩杜纳", 1045);
            /**
             * The world 海猫茶屋
             */
            public final World 海猫茶屋 = World.of("海猫茶屋", 1177);
            /**
             * The world 柔风海湾
             */
            public final World 柔风海湾 = World.of("柔风海湾", 1178);
            /**
             * The world 琥珀原
             */
            public final World 琥珀原 = World.of("琥珀原", 1179);

            @Override
            public List<World> worlds() {
                return List.of(紫水栈桥, 延夏, 静语庄园, 摩杜纳, 海猫茶屋, 柔风海湾, 琥珀原);
            }

            @Override
            public int id() {
                return -1;
            }

        }

        /**
         * The 豆豆柴date center
         */
        public static final class 豆豆柴 implements DataCenter {
            /**
             * The world 水晶塔
             */
            public final World 水晶塔 = World.of("水晶塔", 1192);
            /**
             * The world 银泪湖
             */
            public final World 银泪湖 = World.of("银泪湖", 1183);
            /**
             * The world 太阳海岸
             */
            public final World 太阳海岸 = World.of("太阳海岸", 1180);
            /**
             * The world 伊修加德
             */
            public final World 伊修加德 = World.of("伊修加德", 1186);
            /**
             * The world 红茶川
             */
            public final World 红茶川 = World.of("红茶川", 1201);
            /**
             * The world 黄金谷
             */
            public final World 黄金谷 = World.of("黄金谷", 1068);
            /**
             * The world 月牙湾
             */
            public final World 月牙湾 = World.of("月牙湾", 1064);
            /**
             * The world 雪松原
             */
            public final World 雪松原 = World.of("雪松原", 1187);

            @Override
            public List<World> worlds() {
                return List.of(水晶塔, 银泪湖, 太阳海岸, 伊修加德, 红茶川, 黄金谷, 月牙湾, 雪松原);
            }

            @Override
            public int id() {
                return -1;
            }
        }
    }
}
